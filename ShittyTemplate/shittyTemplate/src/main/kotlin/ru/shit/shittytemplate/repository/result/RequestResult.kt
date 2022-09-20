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

    companion object {

        @JvmStatic
        fun <T> fail(): RequestResult<T> = RequestResult(Result.FAIL)

        @JvmStatic
        fun <T> success(): RequestResult<T> = RequestResult(Result.SUCCESS)
    }
}