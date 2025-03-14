package com.spring.demo.service

import org.springframework.stereotype.Service

@Service
class BalanceValidator : BaseValidator() {
    fun validateHeader(rawContent: String, line: Int): String {
        val totalField = 5
        val ruleHeader = mapOf(
            "asOfDate" to listOf(isRequired, isDate),
            "amcCode" to listOf(isRequired, { value -> max(value, 15) }),
            "totalRecord" to listOf(isRequired, isNumber, { value -> exceed(value.toInt(), 1_500_000) }),
            "totalFund" to listOf(isRequired, isNumber, { value -> exceed(value.toInt(), 99_999_999) }),
            "version" to listOf(isRequired, { value -> max(value, 5) })
        )
        return validate(rawContent, line, totalField, ruleHeader)
    }

    private fun validate(rawContent: String, line: Int, totalField: Int, rules: Map<String, List<(String) -> String?>>): String {
        val fields = rawContent.split("|")
        if (fields.size != totalField) {
            return "line $line: invalid format"
        }
        for ((index, field) in fields.withIndex()) {
            val fieldName = rules.keys.elementAtOrNull(index)
            if (fieldName == null) {
                return "line $line: invalid format"
            }
            val error = rules[fieldName]?.mapNotNull { it(field) }?.firstOrNull()
            if (error != null) {
                return "line $line: $fieldName $error"
            }
        }
        return ""
    }
}