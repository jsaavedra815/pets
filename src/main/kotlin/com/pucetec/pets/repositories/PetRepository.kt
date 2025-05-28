package com.pucetec.pets.repositories

import com.pucetec.pets.models.entities.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository: JpaRepository<Pet, Long>