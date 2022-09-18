package ru.shit.shittytemplate.repository

import ru.shit.shittytemplate.entity.Entity
import ru.shit.shittytemplate.repository.params.Parameters
import ru.shit.shittytemplate.repository.result.RequestResult


abstract class Repository {

    abstract fun get(params: Parameters): RequestResult<Entity>

    abstract fun set(params: Parameters): RequestResult<Entity>

    abstract fun delete(params: Parameters): RequestResult<Entity>
}
