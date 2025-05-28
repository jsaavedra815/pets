package com.pucetec.pets.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "owner_pets")
data class OwnerPet(
    @ManyToOne
    @JoinColumn(name = "owner_id")
    val owner: Owner,

    @ManyToOne
    @JoinColumn(name = "pet_id")
    val pet: Pet,

    val adoptionDate: LocalDate,
    val isPrimary: Boolean
) : BaseEntity()