package ru.shit.shittytemplate.controller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.shit.shittytemplate.entity.Person
import ru.shit.shittytemplate.repository.PersonsRepository
import ru.shit.shittytemplate.repository.params.PersonsParameters
import ru.shit.shittytemplate.repository.result.RequestResult
import java.net.URI

@Controller
@RequestMapping("/api/v1/persons")
class PersonsController {

    @GetMapping("", "/")
    @ResponseBody
    fun getPersons(): ResponseEntity<Array<Person>> =
        ResponseEntity.ok(PersonsRepository.get(PersonsParameters()).get())

    @GetMapping("/{id}")
    @ResponseBody
    fun getPerson(@PathVariable id: Int): ResponseEntity<Person> {
        val person = PersonsRepository.get(PersonsParameters(id)).get()?.getOrNull(0)

        return ResponseEntity(
            person,
            if (person != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }
    @PostMapping("", "/")
    @ResponseStatus
    fun createPerson(@RequestBody person: Person): ResponseEntity<String> {
        val newId = PersonsRepository.add(PersonsParameters(newPerson = person)).get()!!.first().id
        return if (newId != -1)
            ResponseEntity.created(URI.create("/api/v1/persons/$newId")).build()
        else
            ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    fun deletePerson(@PathVariable id: Int): ResponseEntity<String> {
        PersonsRepository.delete(PersonsParameters(id))
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    @ResponseStatus
    fun patchPerson(@PathVariable id: Int, @RequestBody newPerson: Person?): ResponseEntity<Person> =
        if (newPerson == null) ResponseEntity.badRequest().build()
        else if (PersonsRepository.set(PersonsParameters(id, newPerson)).result != RequestResult.Result.SUCCESS) ResponseEntity.notFound().build()
        else ResponseEntity.ok(getPerson(id).body)
}