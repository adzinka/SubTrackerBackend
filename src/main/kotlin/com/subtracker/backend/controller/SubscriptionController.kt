package com.subtracker.backend.controller

import com.subtracker.backend.dto.SubscriptionDto
import com.subtracker.backend.dto.SubscriptionRequestDto
import com.subtracker.backend.service.SubscriptionService
import com.subtracker.backend.model.Subscription
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController(private val subscriptionService: SubscriptionService) {
    @GetMapping("/subscriptions")
    fun getSubscriptions(): List<SubscriptionDto> = subscriptionService.getAll()

    @GetMapping("/subscriptions/{id}")
    fun getSubscriptionById(@PathVariable id: Int): SubscriptionDto = subscriptionService.getById(id)

    @PostMapping("/subscriptions")
    fun addSubscription(
        @Valid @RequestBody
        dto: SubscriptionRequestDto
    ): SubscriptionDto = subscriptionService.add(dto)

    @PutMapping("/subscriptions/{id}")
    fun updateSubscription(
        @PathVariable id: Int,
        @RequestBody dto: SubscriptionRequestDto
    ): SubscriptionDto? = subscriptionService.update(id, dto)

    @DeleteMapping("/subscriptions/{id}")
    fun deleteSubscription(@PathVariable id: Int) {
        subscriptionService.delete(id)
    }
}