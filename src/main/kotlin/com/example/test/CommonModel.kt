package com.example.test

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Common Latvija.lv model.
 */
interface CommonModel {
    @get:JsonProperty("sender_id")
    val senderId: String
    val version: String

    /**
     * Returns fields used for signing with correct order.
     */
    @JsonIgnore
    fun getFieldsForSign(): List<String>
}
