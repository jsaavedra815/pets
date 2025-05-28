package com.pucetec.pets.models.responses

import java.time.LocalDate

data class PetResponse(
    val id: Long,
    val name: String,
    val species: String,
    val birthDate: LocalDate,
    val owners: List<OwnerSummaryResponse>
)