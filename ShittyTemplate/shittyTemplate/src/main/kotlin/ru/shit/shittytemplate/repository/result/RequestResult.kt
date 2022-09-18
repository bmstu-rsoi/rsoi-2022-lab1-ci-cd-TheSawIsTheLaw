package ru.shit.shittytemplate.repository.result

class RequestResult<T>(
    val result: Result,
    private val returnedValues: Array<T>?
) {

    enum class Result {
        SUCCESS,
        ERROR
    }

    fun get(): Array<T>? = returnedValues
}