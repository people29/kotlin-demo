package example

import java.util.*

fun String.toSlug(): String {
    return lowercase(Locale.getDefault())
        .replace(" ", "-")
        .replace("[^a-z0-9-]".toRegex(), "")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")
}

fun String.camelCaseToWords(): String {
    return this.replace(Regex("([a-z])([A-Z])"), "$1 $2").lowercase()
}