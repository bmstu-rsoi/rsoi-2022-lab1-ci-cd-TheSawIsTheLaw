package ru.shit.shittytemplate.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/v1/persons")
class PersonsController {

    @GetMapping("", "/")
    @ResponseBody
    fun getPersons(): String {
        return "Just lol"
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getPerson(@PathVariable id: Int): String {
        return "Lol, it's $id"
    }
}