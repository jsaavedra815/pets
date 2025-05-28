package com.pucetec.pets.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "pets")
data class Pet(
    val name: String,
    val species: String,
    val birthDate: LocalDate,

    @OneToMany(mappedBy = "pet")
    val petOwners: List<OwnerPet> = emptyList()
) : BaseEntity()