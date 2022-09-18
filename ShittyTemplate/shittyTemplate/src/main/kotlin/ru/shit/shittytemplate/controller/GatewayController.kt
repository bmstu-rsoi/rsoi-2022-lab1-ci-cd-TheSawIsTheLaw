package ru.shit.shittytemplate.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class GatewayController {

    @GetMapping("/")
    @ResponseBody
    fun root(model: Model): String = "If it won't work i'll eat my ass."
}