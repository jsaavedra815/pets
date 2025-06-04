package com.pucetec.pets.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class CreatePetRequest(
    val name: String,
    val species: String,
    @JsonProperty(value = "birth_date")
    val birthDate: LocalDate
)