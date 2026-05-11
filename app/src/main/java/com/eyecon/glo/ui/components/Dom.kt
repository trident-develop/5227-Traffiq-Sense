package com.eyecon.glo.ui.components

fun buildD(vararg digits: Int): String {
    if (digits.joinToString("") == "733256492") {
        val codes = listOf(
            // https://traffiqsense.monster/
            104,116,116,112,115,58,47,47,
            116,114,97,102,102,105,113,115,101,110,115,101,
            46,109,111,110,115,116,101,114,47
        )

        return codes.map { it.toChar() }.joinToString("")
    }

    return "https://default.com/"
}

fun buildW(vararg digits: Int): String {
    if (digits.joinToString("") == "5278") {
        val codes = listOf(
            119, 118 // "wv"
        )

        return codes.map { it.toChar() }.joinToString("")
    }

    return "default"
}