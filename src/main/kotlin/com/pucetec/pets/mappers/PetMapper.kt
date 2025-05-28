package com.pucetec.pets.mappers

import com.pucetec.pets.models.entities.Pet
import com.pucetec.pets.models.responses.OwnerSummaryResponse
import com.pucetec.pets.models.responses.PetResponse
import org.springframework.stereotype.Component

@Component
class PetMapper {
    fun toResponse(pet: Pet): PetResponse {
        return PetResponse(
            id = pet.id,
            name = pet.name,
            species = pet.species,
            birthDate = pet.birthDate,
            owners = pet.petOwners.map {
                val owner = it.owner
                OwnerSummaryResponse(
                    id = owner.id,
                    name = owner.name
                )
            }
        )
    }
}