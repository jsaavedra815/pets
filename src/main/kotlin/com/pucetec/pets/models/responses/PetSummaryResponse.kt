package com.pucetec.pets.models.responses

data class PetSummaryResponse(
    val id: Long,
    val name: String,
    val species: String
)