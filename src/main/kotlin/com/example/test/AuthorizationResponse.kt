package com.example.test

/**
 * Authorization response to Latvija.lv.
 */
data class AuthorizationResponse(
    val info: String,
    val date: String,
    val user: String,
    val time: String,
    override val senderId: String,
    override val version: String
) : CommonResponse {

    override val type = ResponseType.AUTHORIZATION

    override lateinit var signature: String

    override fun getFieldsForSign() = listOf(
        type.code, version, senderId, info, user, date, time
    )
}
