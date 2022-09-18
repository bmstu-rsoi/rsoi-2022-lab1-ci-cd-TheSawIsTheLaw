package data

import org.jetbrains.exposed.sql.Table

object PersonsTable: Table() {

    val mId = integer("id").autoIncrement()
    val mName = varchar("name", 50)
    val mAge = integer("age")
    val mAddress = varchar("address", 100)
    val mWork = varchar("work", 100)
}