package com.example.prac

fun <T> Collection<T>.customJoinToString(
    separator: String = "; ",
    prefix: String = "<",
    postfix: String = ">"
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)

    return result.toString()
}
