package com.pucetec.pets.mappers

import com.pucetec.pets.models.entities.Owner
import com.pucetec.pets.models.responses.OwnerResponse
import com.pucetec.pets.models.responses.PetSummaryResponse
import org.springframework.stereotype.Component

@Component
class OwnerMapper {
    fun toResponse(owner: Owner): OwnerResponse {
        return OwnerResponse(
            id = owner.id,
            name = owner.name,
            age = owner.age,
            pets = owner.ownerPets.map {
                val pet = it.pet
                PetSummaryResponse(
                    id = pet.id,
                    name = pet.name,
                    species = pet.species
                )
            }
        )
    }
}