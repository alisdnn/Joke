package com.alisdnn.presentation.util

class Converter {

    fun stringListToString(list: List<String>): String {
        var output = ""
        for (i in list) {
            output += ", $i"
        }
        return output
    }
}