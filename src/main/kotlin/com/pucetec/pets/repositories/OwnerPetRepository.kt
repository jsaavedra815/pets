package com.pucetec.pets.repositories

import com.pucetec.pets.models.entities.Owner
import com.pucetec.pets.models.entities.OwnerPet
import com.pucetec.pets.models.entities.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OwnerPetRepository: JpaRepository<OwnerPet, Long>{

    /**
     * SELECT EXISTS (
     *   SELECT 1
     *   FROM owner_pet
     *   WHERE owner_id = :ownerId AND pet_id = :petId
     * );
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM OwnerPet p WHERE p.owner.id = :ownerId AND p.pet.id = :petId")
    fun existsByOwnerAndPetId(ownerId: Long, petId: Long): Boolean
}

