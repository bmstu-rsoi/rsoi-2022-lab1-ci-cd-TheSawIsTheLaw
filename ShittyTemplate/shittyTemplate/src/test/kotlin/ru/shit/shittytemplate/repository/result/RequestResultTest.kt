package ru.shit.shittytemplate.repository.result

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RequestResultTest {

    @Test
    fun `SUCCESS RESULT STATIC`() {
        val shitResult = RequestResult.success<Any>()

        assertEquals(RequestResult.Result.SUCCESS, shitResult.result)
    }

    @Test
    fun `FAIL RESULT STATIC`() {
        val shitResult = RequestResult.fail<Any>()

        assertEquals(RequestResult.Result.FAIL, shitResult.result)
    }
}