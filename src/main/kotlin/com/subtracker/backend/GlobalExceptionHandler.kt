package com.subtracker.backend

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage }
        return ResponseEntity.badRequest().body(
            mapOf(
                "status" to 400,
                "message" to "Validation failed",
                "errors" to errors
            )
        )
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(404).body(
            mapOf(
                "status" to 404,
                "message" to "Not found"
            )
        )
    }
}