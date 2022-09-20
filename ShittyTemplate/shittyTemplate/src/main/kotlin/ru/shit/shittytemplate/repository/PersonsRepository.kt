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

    private val database by lazy {
        Database.connect(
            Hardcode.POSTGRES_DB_ADDRESS,
            "org.postgresql.Driver",
            Hardcode.POSTGRES_USER,
            Hardcode.POSTGRES_PASSWORD
        )
    }

    override fun get(params: PersonsParameters): RequestResult<Person> =
        transaction(database) {
            val preset =
                if (params.personId == -1) {
                    PersonsTable.selectAll()
                } else {
                    PersonsTable.select(mId eq params.personId)
                }

            preset.map { Person(it[mId], it[mName], it[mAge], it[mAddress], it[mWork]) }
                    .toTypedArray().let { shit -> RequestResult(RequestResult.Result.SUCCESS, shit) }
        }

    override fun add(params: PersonsParameters): RequestResult<Person> =
        if (params.newPerson != null) {
            val newPerson = params.newPerson
            var recordId = 0
            transaction(database) {
                recordId = PersonsTable.insert {
                    if (newPerson.age != null) it[mAge] = newPerson.age
                    if (newPerson.name != null) it[mName] = newPerson.name
                    if (newPerson.work != null) it[mWork] = newPerson.work
                    if (newPerson.address != null) it[mAddress] = newPerson.address
                }.resultedValues!!.first()[mId]
            }

            RequestResult(RequestResult.Result.SUCCESS, arrayOf(Person(recordId)))
        } else
            RequestResult(RequestResult.Result.FAIL)

    override fun set(params: PersonsParameters): RequestResult<Person> =
        if (params.newPerson != null) {
            val newPerson = params.newPerson
            var updated = 0
            transaction(database) {
                updated = PersonsTable.update({ mId eq params.personId }) {
                    if (newPerson.age != null) it[mAge] = newPerson.age
                    if (newPerson.name != null) it[mName] = newPerson.name
                    if (newPerson.work != null) it[mWork] = newPerson.work
                    if (newPerson.address != null) it[mAddress] = newPerson.address
                }
            }

            if (updated > 0) RequestResult.success() else RequestResult.fail()
        } else {
            RequestResult.fail()
        }

    override fun delete(params: PersonsParameters): RequestResult<Person> =
        transaction(database) {
            PersonsTable.deleteWhere {
                mId eq params.personId
            }
        }.let { RequestResult(RequestResult.Result.SUCCESS) }
}