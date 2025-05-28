package com.pucetec.pets.controllers

import com.pucetec.pets.models.requests.AssignPetToOwnerRequest
import com.pucetec.pets.models.requests.CreateOwnerRequest
import com.pucetec.pets.models.responses.OwnerResponse
import com.pucetec.pets.routes.Routes
import com.pucetec.pets.services.OwnerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.BASE_URL + Routes.OWNERS)
class OwnerController(
    private val ownerService: OwnerService
) {
    @PostMapping
    fun createOwner(@RequestBody request: CreateOwnerRequest): OwnerResponse {
        return ownerService.createOwner(request)
    }

    @PostMapping(Routes.ASSIGN)
    fun assignPet(@RequestBody request: AssignPetToOwnerRequest): OwnerResponse {
        return ownerService.assignPetToOwner(request)
    }
}