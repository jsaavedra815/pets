package com.pucetec.pets.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class AssignPetToOwnerRequest(
    @JsonProperty("owner_id")
    val ownerId: Long,
    @JsonProperty("pet_id")
    val petId: Long,
    @JsonProperty("adoption_date")
    val adoptionDate: LocalDate,
    @JsonProperty("is_primary")
    val isPrimary: Boolean
)