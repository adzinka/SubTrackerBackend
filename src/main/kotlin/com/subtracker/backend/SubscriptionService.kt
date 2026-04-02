package com.subtracker.backend

import org.springframework.stereotype.Service
import java.util.Optional

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository,
) {

    fun getAll(): List<Subscription> = subscriptionRepository.findAll()

    fun getById(id: Int): Subscription? = subscriptionRepository.findById(id).orElse(null)

    fun add(subscription: Subscription): Subscription {
        return subscriptionRepository.save(subscription)
    }

    fun update(id: Int, subscription: Subscription): Subscription? {
        if (!subscriptionRepository.existsById(id)) return null
        return subscriptionRepository.save(subscription.copy(id = id))
    }

    fun delete(id: Int) {
        subscriptionRepository.deleteById(id)
    }
}