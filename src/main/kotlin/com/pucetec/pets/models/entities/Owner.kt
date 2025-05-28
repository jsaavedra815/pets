package com.pucetec.pets.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "owners")
data class Owner(
    val name: String,
    val age: Int,

    @OneToMany(mappedBy = "owner")
    val ownerPets: List<OwnerPet> = emptyList()
) : BaseEntity()