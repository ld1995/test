package com.example.test

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Signature
import java.security.cert.Certificate
import java.util.*

@Component
class SignatureHelper {

    private val logger: Logger = LoggerFactory.getLogger(SignatureHelper::class.java)

    fun verify(signature: String, params: String, certificate: Certificate): Boolean {
        logger.info("Input check signature data : $signature $params")
        val sig: Signature = Signature.getInstance("SHA1WithRSA")
        sig.initVerify(certificate)
        sig.update(params.toByteArray(StandardCharsets.UTF_8))
        return sig.verify(Base64.getDecoder().decode(signature))
    }
}