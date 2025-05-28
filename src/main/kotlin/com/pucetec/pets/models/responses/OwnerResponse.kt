package com.pucetec.pets.models.responses

data class OwnerResponse(
    val id: Long,
    val name: String,
    val age: Int,
    val pets: List<PetSummaryResponse>
)