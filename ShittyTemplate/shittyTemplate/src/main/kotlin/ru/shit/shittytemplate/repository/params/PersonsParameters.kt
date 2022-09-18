package ru.shit.shittytemplate.repository.params

import ru.shit.shittytemplate.entity.Person

class PersonsParameters(
    val personId: Int = -1,
    val newPerson: Person?
): Parameters()