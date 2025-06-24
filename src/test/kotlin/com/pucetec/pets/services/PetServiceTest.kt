package com.pucetec.pets.services

import com.pucetec.pets.exceptions.exceptions.ResourceNotFoundException
import com.pucetec.pets.mappers.PetMapper
import com.pucetec.pets.models.entities.Owner
import com.pucetec.pets.models.entities.OwnerPet
import com.pucetec.pets.models.entities.Pet
import com.pucetec.pets.repositories.PetRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.LocalDate
import java.util.Optional

class PetServiceTest {

    private lateinit var petRepositoryMock: PetRepository
    private lateinit var petMapper: PetMapper

    private lateinit var service: PetService

    @BeforeEach
    fun carga(){
        // esto se va a ejecutar antes de todos los tests
        petRepositoryMock = mock(PetRepository::class.java)
        petMapper = PetMapper()

        service = PetService(petRepositoryMock, petMapper)
    }


    @Test
    fun should_get_a_pet_by_id() {

        val artificialPet = Pet(
            name = "Simon",
            species = "Dog",
            birthDate = LocalDate.now(),
        )

        val artificialPet2 = Pet(
            name = "Pimienta",
            species = "Cat",
            birthDate = LocalDate.now(),
        )

        // Como usar mockito.when
        // 1. Llamandolo directamente por el nombre de su clase padre
        // 2. usando backticks (`)

        `when`(petRepositoryMock.findById(1L))
            .thenReturn(Optional.of(artificialPet))

        `when`(petRepositoryMock.findById(3L))
            .thenReturn(Optional.of(artificialPet2))

        val petResponse = service.getPetById(1L)

        assertEquals("Simon",  petResponse.name)
        assertEquals("Dog",  petResponse.species)

        assertThrows<ResourceNotFoundException> {
            service.getPetById(2L)
        }

        val petResponse3 = service.getPetById(3L)

        assertEquals("Pimienta",  petResponse3.name)
        assertEquals("Cat",  petResponse3.species)

    }


    @AfterEach
    fun descarga(){
        // esto se va a ejecutar despues de todos los tests
    }

}

/***
 * MOCKS
 * Que es un mock?
 * Un mock es un conjunto de datos que simulan el real
 *
 * Como vamos a usar los mocks?
 * Lo haremos usando una libreria llamada Mockito
 *
 * Ciclo de vida de los tests
 *
 * ciclo de carga: @BeforeEach
 *
 * ciclo de pruebas: @Test
 *
 * ciclo de descarga @AfterEach
 *
 *
 * eliges un servicio que tenga al menos dos metodos
 * y escribes tests al 100% PARA ESE SERVICIO
 */