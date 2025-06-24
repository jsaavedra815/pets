package com.pucetec.pets.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.pucetec.pets.exceptions.exceptions.ResourceNotFoundException
import com.pucetec.pets.models.requests.CreatePetRequest
import com.pucetec.pets.models.responses.PetResponse
import com.pucetec.pets.routes.Routes
import com.pucetec.pets.services.PetService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.LocalDate
import kotlin.test.assertEquals

@WebMvcTest(PetController::class)
@Import(MockConfig::class)
class PetControllerTest {

    // Inyectar el MockMvc para poder hacer las peticiones HTTP
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var petService: PetService

    // Inyectar el ObjectMapper para poder serializar y deserializar JSON
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun carga() {
        // MUY IMPORTANTE
        // Inicializar el ObjectMapper antes de cada test
        // porque si no, no se va a poder serializar LocalDate
        // y va a dar un error de tipo "No serializer found for class java.time.LocalDate"
        objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    }

    val BASE_URL = Routes.BASE_URL + Routes.PETS

    @Test
    fun should_return_pet_when_get_by_id() {
        val pet = PetResponse(
            id = 1L,
            name = "Luna",
            species = "Cat",
            birthDate = LocalDate.of(2020, 5, 10),
            owners = listOf()
        )

        `when`(petService.getPetById(1L)).thenReturn(pet)

        val result = mockMvc.get("$BASE_URL/1")
            .andExpect {
                status { isOk() }
                // aquí puedes verificar el contenido de la respuesta antes de parsearlo
                jsonPath("$.name") { value("Luna") }
                jsonPath("$.species") { value("Cat") }
            }.andReturn()

        // Verifica el estado de la respuesta una vez que se ha parseado
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_get_a_non_existent_id() {

        val petId = 1L

        `when`(petService.getPetById(petId)).thenThrow(ResourceNotFoundException("Pet with ID $petId not found"))

        val result = mockMvc.get("$BASE_URL/1")
            // dos maneras de hacer lo mismo (chequear el status)
            .andExpect {
                status { isNotFound() }
            }.andReturn()

        assertEquals(404, result.response.status)
    }

    @Test
    fun should_create_pet_when_post() {
        val request = CreatePetRequest(
            name = "Toby",
            species = "Dog",
            birthDate = LocalDate.of(2019, 3, 15)
        )

        val response = PetResponse(
            id = 2L,
            name = "Toby",
            species = "Dog",
            birthDate = request.birthDate,
            owners = listOf()
        )

        `when`(petService.createPet(request)).thenReturn(response)

        val json = objectMapper.writeValueAsString(request)

        val result = mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.id") { value(2) }
            jsonPath("$.name") { value("Toby") }
            jsonPath("$.species") { value("Dog") }
        }.andReturn()

        assertEquals(200, result.response.status)
    }
}

@TestConfiguration
class MockConfig {
    @Bean
    fun petService(): PetService = mock(PetService::class.java)
}