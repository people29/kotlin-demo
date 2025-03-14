package com.spring.demo.service

open class BaseValidator {
    val isRequired: (String) -> String? = { value -> if (value.isEmpty()) "is required" else null }
    val isNumber: (String) -> String? = { value -> if (value.toIntOrNull() == null) "is invalid" else null }
    val max: (String, Int) -> String? = { value, limit -> if (value.length > limit) "is invalid" else null }
    val exceed: (Int, Int) -> String? = { value, limit -> if (value > limit) "is invalid" else null }
    val isDate: (String) -> String? = { value -> if (value.matches(Regex("\\d{8}"))) null else "is invalid" }
}