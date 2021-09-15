package com.example.test

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Response codes.
 */
enum class ResponseType(
    @get:JsonValue val code: String
) {
    PAYMENT_SUCCESS("1101"),
    PAYMENT_FAILED("1901"),
    AUTHORIZATION("3002");

    @JsonCreator
    fun create(code: String): ResponseType = values()
        .first { it.code == code }
}
