package ru.shit.shittytemplate.repository

import data.PersonsTable
import data.PersonsTable.mAddress
import data.PersonsTable.mAge
import data.PersonsTable.mId
import data.PersonsTable.mName
import data.PersonsTable.mWork
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.shit.shittytemplate.entity.Person
import ru.shit.shittytemplate.govnohardcode.Hardcode
import ru.shit.shittytemplate.repository.params.PersonsParameters
import ru.shit.shittytemplate.repository.result.RequestResult

object PersonsRepository : Repository<PersonsParameters, Person> {

    private val database by lazy { Database.connect(
        Hardcode.POSTGRES_DB_ADDRESS,
        "org.postgresql.Driver",
        Hardcode.POSTGRES_USER,
        Hardcode.POSTGRES_PASSWORD
    ) }

    override fun get(params: PersonsParameters): RequestResult<Person> =
        transaction(database) {
            PersonsTable.select(mId eq params.personId).map { Person(it[mId], it[mName], it[mAge], it[mAddress], it[mWork]) }
                    .toTypedArray().let { shit -> RequestResult(RequestResult.Result.SUCCESS, shit) }
        }

    override fun set(params: PersonsParameters): RequestResult<Person> =
        if (params.newPerson != null) {
            val newPerson = params.newPerson
            transaction(database) {
                PersonsTable.update({ mId eq params.personId }) {
                    it[mAge] = newPerson.mAge
                    it[mName] = newPerson.mName
                    it[mWork] = newPerson.mWork
                    it[mAddress] = newPerson.mAddress
                }
            }

            RequestResult(RequestResult.Result.SUCCESS)
        } else {
            RequestResult(RequestResult.Result.FAIL)
        }

    override fun delete(params: PersonsParameters): RequestResult<Person> =
        transaction(database) {
            val result = PersonsTable.deleteWhere {
                mId eq params.personId
            }

            return@transaction if (result > 0)
                RequestResult(RequestResult.Result.SUCCESS)
            else
                RequestResult(RequestResult.Result.FAIL)
        }
}