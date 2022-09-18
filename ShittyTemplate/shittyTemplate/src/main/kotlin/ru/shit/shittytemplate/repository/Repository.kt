package ru.shit.shittytemplate.repository

import ru.shit.shittytemplate.entity.Entity
import ru.shit.shittytemplate.repository.params.Parameters
import ru.shit.shittytemplate.repository.result.RequestResult


interface Repository<T: Parameters, R: Entity> {

    fun get(params: T): RequestResult<R>

    fun set(params: T): RequestResult<R>

    fun delete(params: T): RequestResult<R>
}
