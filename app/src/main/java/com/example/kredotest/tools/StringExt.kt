package com.example.kredotest.tools

fun String.mapToCountFormat(): String {
    if (length < 8) return this
    return "${take(4)}...${substring((length - 8)..<length - 4)} ${takeLast(4)}"
}

fun String.mapToCardFormat(): String {
    if (length == 4) return "** $this"
    return "** ${takeLast(4)}"
}

fun String.mapAmount(): String {
    if (length < 4) return "$this ₴"
    return "${take(length - 4)} ${takeLast(4)} ₴"
}