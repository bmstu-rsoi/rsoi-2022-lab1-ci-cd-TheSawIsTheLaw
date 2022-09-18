package ru.shit.shittytemplate.controller
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.shit.shittytemplate.entity.Person

@Controller
@RequestMapping("/api/v1/persons")
class PersonsController {

    @GetMapping("", "/")
    @ResponseBody
    fun getPersons(): String {
        return "Just lol" // 200
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getPerson(@PathVariable id: Int): String {
        return "Lol, it's $id" // 200 404
    }

    @PostMapping("", "/")
    @ResponseStatus
    fun createPerson(@RequestBody person: Person): ResponseStatus {
        return ResponseStatus(HttpStatus.CREATED) // 201 400
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    fun deletePerson(@PathVariable id: Int): ResponseStatus {
        return ResponseStatus(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    @ResponseStatus
    fun patchPerson(@PathVariable id: Int): ResponseStatus {
        return ResponseStatus(HttpStatus.OK) // 404 400
    }
}