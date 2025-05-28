package com.pucetec.pets.models.requests

import java.time.LocalDate

data class CreatePetRequest(
    val name: String,
    val species: String,
    val birthDate: LocalDate
)