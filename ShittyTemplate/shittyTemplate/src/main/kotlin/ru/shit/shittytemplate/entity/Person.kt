package ru.shit.shittytemplate.entity

class Person(
    var id: Int,
    val name: String? = null,
    val age: Int? = null,
    val address: String? = null,
    val work: String? = null
): Entity()