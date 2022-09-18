package ru.shit.shittytemplate.repository

import ru.shit.shittytemplate.entity.Entity
import ru.shit.shittytemplate.repository.params.Parameters
import ru.shit.shittytemplate.repository.result.RequestResult

class PersonsRepository : Repository() {

    override fun get(params: Parameters): RequestResult<Entity> {
        TODO("Not yet implemented")
//        return RequestResult(RequestResult.Result.SUCCESS, arrayOf(Person(0, "lox", 134, "33", "33")))
    }

    override fun set(params: Parameters): RequestResult<Entity> {
        TODO("Not yet implemented")
    }

    override fun delete(params: Parameters): RequestResult<Entity> {
        TODO("Not yet implemented")
    }
}