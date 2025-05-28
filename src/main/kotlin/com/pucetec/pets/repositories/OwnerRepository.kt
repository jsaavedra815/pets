package com.pucetec.pets.repositories

import com.pucetec.pets.models.entities.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository: JpaRepository<Owner, Long>