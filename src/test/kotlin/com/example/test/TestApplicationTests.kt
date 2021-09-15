package com.example.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TestApplicationTests {

    @Test
    internal fun name() {
        assertEquals("3002", ResponseType.AUTHORIZATION.code)
        assertEquals("1101", ResponseType.PAYMENT_SUCCESS.code)
    }
}
