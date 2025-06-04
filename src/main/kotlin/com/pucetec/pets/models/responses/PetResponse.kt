package com.pucetec.pets.models.responses

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class PetResponse(
    val id: Long,
    val name: String,
    val species: String,
    @JsonProperty("birth_date") val dateOfBirth: LocalDate,
    val birthDate: LocalDate,
    val owners: List<OwnerSummaryResponse>
)