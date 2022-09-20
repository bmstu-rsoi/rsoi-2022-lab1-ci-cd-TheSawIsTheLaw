package ru.shit.shittytemplate.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.shit.shittytemplate.repository.params.PersonsParameters
import ru.shit.shittytemplate.repository.result.RequestResult

internal class PersonsRepositoryTest {

    @Test
    fun `PERSON NULL IN ADD`() {
        val shitResponse = PersonsRepository.add(PersonsParameters(3))

        assertEquals(RequestResult.Result.FAIL, shitResponse.result)
    }

    @Test
    fun `PERSON NULL IN SET`() {
        val shitResponse = PersonsRepository.set(PersonsParameters())

        assertEquals(RequestResult.Result.FAIL, shitResponse.result)
    }
}