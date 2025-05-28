package com.pucetec.pets.exceptions.handlers

import com.pucetec.pets.exceptions.exceptions.DuplicateAssignmentException
import com.pucetec.pets.exceptions.exceptions.InvalidRequestException
import com.pucetec.pets.exceptions.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to ex.message.orEmpty()), HttpStatus.NOT_FOUND)

    @ExceptionHandler(DuplicateAssignmentException::class)
    fun handleDuplicate(ex: DuplicateAssignmentException): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to ex.message.orEmpty()), HttpStatus.CONFLICT)

    @ExceptionHandler(InvalidRequestException::class)
    fun handleInvalid(ex: InvalidRequestException): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to ex.message.orEmpty()), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to "Unexpected error: ${ex.message}"), HttpStatus.INTERNAL_SERVER_ERROR)
}