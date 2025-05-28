package com.pucetec.pets.controllers

import com.pucetec.pets.models.requests.CreatePetRequest
import com.pucetec.pets.models.responses.PetResponse
import com.pucetec.pets.routes.Routes
import com.pucetec.pets.services.PetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.BASE_URL + Routes.PETS)
class PetController(
    private val petService: PetService
) {
    @PostMapping
    fun createPet(@RequestBody request: CreatePetRequest): PetResponse {
        return petService.createPet(request)
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable id: Long): PetResponse {
        return petService.getPetById(id)
    }
}