package ru.shit.shittytemplate.controller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.shit.shittytemplate.entity.Person
import ru.shit.shittytemplate.repository.PersonsRepository
import ru.shit.shittytemplate.repository.params.PersonsParameters

@Controller
@RequestMapping("/api/v1/persons")
class PersonsController {

    @GetMapping("", "/")
    @ResponseBody
    fun getPersons(): ResponseEntity<Array<Person>> =
        ResponseEntity(
            PersonsRepository.get(PersonsParameters()).get(), HttpStatus.OK
        )

    @GetMapping("/{id}")
    @ResponseBody
    fun getPerson(@PathVariable id: Int): String {
        return "Lol, it's $id" // 200 404
    }

    @PostMapping("", "/")
    @ResponseStatus
    fun createPerson(@RequestBody person: Person): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.CREATED) // 201 400 + id
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    fun deletePerson(@PathVariable id: Int): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    @ResponseStatus
    fun patchPerson(@PathVariable id: Int): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.OK) // 404 400
    }
}

//fun main() {
//    Database.connect(Hardcode.POSTGRES_DB_ADDRESS, "org.postgresql.Driver", Hardcode.POSTGRES_USER, Hardcode.POSTGRES_PASSWORD)
//
//    transaction {
//        addLogger(StdOutSqlLogger)
//        PersonsTable.insert {
//            it[mName] = "VnatureLox333"
//            it[mAge] = 42
//            it[mAddress] = "hz"
//            it[mWork] = "nothing"
//        }
//    }
//    transaction {
//        println( PersonsTable.selectAll().map { it[mId] })
//    }
//}