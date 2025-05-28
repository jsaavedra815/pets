package com.pucetec.pets.models.requests

import java.time.LocalDate

data class AssignPetToOwnerRequest(
    val ownerId: Long,
    val petId: Long,
    val adoptionDate: LocalDate,
    val isPrimary: Boolean
)