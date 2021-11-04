package com.example.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*


class TestApplicationTests {

    @Test
    internal fun name() {
        assertEquals("3003", ResponseType.AUTHORIZATION.code)
        assertEquals("1101", ResponseType.PAYMENT_SUCCESS.code)
    }
}
