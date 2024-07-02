package com.example.healthhub.data.util

val String.Companion.BLANK: String
    get() = ""

val String.Companion.SPACE: String
    get() = " "

val String.Companion.BULLET: String
    get() = "\u2022"

val String.Companion.NEW_LINE: String
    get() = System.lineSeparator()

val String.Companion.SLASH: String
    get() = "/"

fun String.getStringWithBullet(): String = String.BULLET + String.SPACE + this + String.NEW_LINE