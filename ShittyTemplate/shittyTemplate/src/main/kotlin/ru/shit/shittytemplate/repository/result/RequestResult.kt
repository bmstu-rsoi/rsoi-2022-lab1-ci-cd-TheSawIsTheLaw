package ru.shit.shittytemplate.repository.result

class RequestResult<T>(
    val result: Result,
    private val returnedValues: Array<T>? = null
) {

    enum class Result {
        SUCCESS,
        FAIL
    }

    fun get(): Array<T>? = returnedValues
}