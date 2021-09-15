package com.example.test

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.binary.Hex
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.File
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Configuration
@SpringBootApplication
class TestApplication {

    @Bean
    fun store(
        @Value("\${keystore.location}") location: String,
        @Value("\${keystore.type}") type: String,
        @Value("\${keystore.storepass}") storepass: String,
        @Value("\${keystore.key.password}") password: String
    ): KeyStore {
        val keyStore = KeyStore.getInstance(type)
        keyStore.load(File(location).inputStream(), PasswordDecryptor.getDecryptedPassword(storepass, password))
        return keyStore
    }

}

/**
 * Converter config to set custom converters in web adapter.
 */
@Configuration
class ConverterConfig(
    private val objectMapper: ObjectMapper
) : WebMvcConfigurer {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val slackSlashCommandConverter = CommonResponseConverter(objectMapper)
        slackSlashCommandConverter.supportedMediaTypes = listOf(MediaType.APPLICATION_FORM_URLENCODED)
        converters.add(slackSlashCommandConverter)
        super.configureMessageConverters(converters)
    }
}

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}


@RestController
class Controller(
    private val store: KeyStore,
    private val signatureHelper: SignatureHelper
) {

    @PostMapping("/data")
    fun data(@RequestBody request: CommonResponse): ResponseEntity<Boolean> {
        val certificate = store.getCertificate(request.senderId)
        val res = signatureHelper.verify(request.signature, getParams(request.getFieldsForSign()), certificate)
        return ResponseEntity.ok(res)
    }

    @GetMapping("/data")
    fun res(): ResponseEntity<String> {
        return ResponseEntity.ok("123")
    }

    private fun getParams(fields: List<String>) = fields.joinToString("")
}

object PasswordDecryptor {
    private const val TRANSFORMATION = "AES"

    /**
     * Decrypts passed password based on storepass.
     */
    fun getDecryptedPassword(storepass: String, password: String): CharArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val decodedPassword = Hex.decodeHex(password.toCharArray())
        val decodedStorepass = Hex.decodeHex(storepass.toCharArray())
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(decodedPassword, TRANSFORMATION))
        return String(cipher.doFinal(decodedStorepass)).toCharArray()
    }
}

/**
 * Custom converter to parse request with content type application/x-www-form-urlencoded as a json.
 */
class CommonResponseConverter(
    private val mapper: ObjectMapper
) : AbstractHttpMessageConverter<CommonResponse>() {

    private val formHttpMessageConverter = FormHttpMessageConverter()

    override fun supports(clazz: Class<*>): Boolean {
        return CommonResponse::class.java == clazz
    }

    override fun readInternal(clazz: Class<out CommonResponse>, inputMessage: HttpInputMessage): CommonResponse {
        return mapper.convertValue(
            formHttpMessageConverter.read(null, inputMessage).toSingleValueMap(),
            CommonResponse::class.java
        )
    }

    override fun writeInternal(p0: CommonResponse, p1: HttpOutputMessage) {
        TODO("Not yet implemented")
    }
}