package com.pucetec.pets.models.requests

data class CreateOwnerRequest(
    val name: String,
    val age: Int
)