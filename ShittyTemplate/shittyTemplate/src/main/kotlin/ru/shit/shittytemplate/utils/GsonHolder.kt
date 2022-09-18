package ru.shit.shittytemplate.utils

import com.google.gson.Gson

object GsonHolder {

    val gson by lazy { Gson() }
}