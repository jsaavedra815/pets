package com.pucetec.pets.services

import com.pucetec.pets.exceptions.exceptions.ResourceNotFoundException
import com.pucetec.pets.mappers.PetMapper
import com.pucetec.pets.models.entities.Pet
import com.pucetec.pets.models.requests.CreatePetRequest
import com.pucetec.pets.models.responses.PetResponse
import com.pucetec.pets.repositories.PetRepository
import org.springframework.stereotype.Service

@Service
class PetService(
    private val petRepository: PetRepository,
    private val petMapper: PetMapper
) {
    fun createPet(request: CreatePetRequest): PetResponse {
        val pet = petRepository.save(
            Pet(
                name = request.name,
                species = request.species,
                birthDate = request.birthDate
            )
        )
        return petMapper.toResponse(pet)
    }

    fun getPetById(id: Long): PetResponse {

        val pet = petRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Pet with ID $id not found")
        }

        val blackList = listOf("Simon", "Joaquin", "Superman")

        if (blackList.contains(pet.name)) {
            throw ResourceNotFoundException("Pet with ID $id not found")
        }

        return petMapper.toResponse(pet)
    }
}