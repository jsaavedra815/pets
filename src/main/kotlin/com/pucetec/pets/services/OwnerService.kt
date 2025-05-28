package com.pucetec.pets.services

import com.pucetec.pets.exceptions.exceptions.DuplicateAssignmentException
import com.pucetec.pets.exceptions.exceptions.ResourceNotFoundException
import com.pucetec.pets.mappers.OwnerMapper
import com.pucetec.pets.models.entities.Owner
import com.pucetec.pets.models.entities.OwnerPet
import com.pucetec.pets.models.requests.AssignPetToOwnerRequest
import com.pucetec.pets.models.requests.CreateOwnerRequest
import com.pucetec.pets.models.responses.OwnerResponse
import com.pucetec.pets.repositories.OwnerPetRepository
import com.pucetec.pets.repositories.OwnerRepository
import com.pucetec.pets.repositories.PetRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(
    private val ownerRepository: OwnerRepository,
    private val petRepository: PetRepository,
    private val ownerPetRepository: OwnerPetRepository,
    private val ownerMapper: OwnerMapper,
) {
    fun createOwner(request: CreateOwnerRequest): OwnerResponse {
        val owner = ownerRepository.save(Owner(name = request.name, age = request.age))
        return ownerMapper.toResponse(owner)
    }

    fun assignPetToOwner(request: AssignPetToOwnerRequest): OwnerResponse {
        val owner = ownerRepository.findById(request.ownerId)
            .orElseThrow { ResourceNotFoundException("Owner with ID ${request.ownerId} not found") }

        val pet = petRepository.findById(request.petId)
            .orElseThrow { ResourceNotFoundException("Pet with ID ${request.petId} not found") }

        val alreadyAssigned = ownerPetRepository.existsByOwnerAndPetId(owner.id, pet.id)
        if (alreadyAssigned) {
            throw DuplicateAssignmentException("This pet is already assigned to this owner") as Throwable
        }

        val ownerPet =
            OwnerPet(owner = owner, pet = pet, adoptionDate = request.adoptionDate, isPrimary = request.isPrimary)
        ownerPetRepository.save(ownerPet)

        val updatedOwner = ownerRepository.findById(request.ownerId).get()
        return ownerMapper.toResponse(updatedOwner)
    }
}