package com.subtracker.backend.service

import com.subtracker.backend.dto.SubscriptionDto
import com.subtracker.backend.dto.toDto
import com.subtracker.backend.model.Subscription
import com.subtracker.backend.repository.SubscriptionRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository,
) {

    fun getAll(): List<SubscriptionDto> = subscriptionRepository.findAll().map { it.toDto() }

    fun getById(id: Int): SubscriptionDto? {
        val subscription = subscriptionRepository.findById(id).orElse(null) ?: return null
        return subscription.toDto()
    }

    fun add(subscription: Subscription): SubscriptionDto {
        return subscriptionRepository.save(subscription).toDto()
    }

    fun update(id: Int, subscription: Subscription): SubscriptionDto? {
        if (!subscriptionRepository.existsById(id)) return null
        return subscriptionRepository.save(subscription.copy(id = id)).toDto()
    }

    fun delete(id: Int) {
        subscriptionRepository.deleteById(id)
    }
}