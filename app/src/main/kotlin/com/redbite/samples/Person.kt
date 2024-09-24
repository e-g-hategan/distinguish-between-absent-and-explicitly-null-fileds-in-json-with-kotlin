package com.redbite.samples

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import java.util.Optional

@JsonInclude(NON_NULL)
data class Person(
    val id: Optional<String>? = null,
    val firstName: Optional<String>? = null,
    val lastName: Optional<String>? = null,
    val dateOfBirth: Optional<DateOfBirth>? = null,
    val maritalStatus: Optional<MaritalStatus>? = null,
    val spouseId: Optional<String>? = null
) {

    data class DateOfBirth(val year: Int, val month: Int, val day: Int)

    enum class MaritalStatus { SINGLE, MARRIED, DIVORCED, WIDOWED }
}