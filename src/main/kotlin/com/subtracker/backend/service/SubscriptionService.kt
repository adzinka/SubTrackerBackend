package com.subtracker.backend.service

import com.subtracker.backend.dto.SubscriptionDto
import com.subtracker.backend.dto.SubscriptionRequestDto
import com.subtracker.backend.dto.toDto
import com.subtracker.backend.dto.toEntity
import com.subtracker.backend.model.Subscription
import com.subtracker.backend.repository.SubscriptionRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository,
) {

    fun getAll(): List<SubscriptionDto> = subscriptionRepository.findAll().map { it.toDto() }

    fun getById(id: Int): SubscriptionDto {
        return subscriptionRepository.findById(id)
            .orElseThrow { NoSuchElementException("Subscription $id not found") }
            .toDto()
    }

    fun add(dto: SubscriptionRequestDto): SubscriptionDto {
        return subscriptionRepository.save(dto.toEntity()).toDto()
    }

    fun update(id: Int, dto: SubscriptionRequestDto): SubscriptionDto? {
        if (!subscriptionRepository.existsById(id)) return null
        return subscriptionRepository.save(dto.toEntity().copy(id = id)).toDto()
    }

    fun delete(id: Int) {
        subscriptionRepository.deleteById(id)
    }
}