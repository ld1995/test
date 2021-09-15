package com.example.test

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 *  Response to Latvija.lv.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    visible = false
)
@JsonSubTypes(
    JsonSubTypes.Type(value = AuthorizationResponse::class, name = "3002"),
)
interface CommonResponse : CommonModel {

    val type: ResponseType

    var signature: String

    override val senderId: String

    override val version: String
}
