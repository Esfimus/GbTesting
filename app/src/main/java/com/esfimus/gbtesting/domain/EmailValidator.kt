package com.esfimus.gbtesting.domain

private const val EMAIL_PATTERN = """[\w-.]+@([\w-]+\.)+\w{2,4}"""

class EmailValidator {
    fun isValid(email: CharSequence?) = (email != null) && EMAIL_PATTERN.toRegex().matches(email.trim())
}