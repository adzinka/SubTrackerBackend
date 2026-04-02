package com.subtracker.backend

import org.springframework.stereotype.Service

@Service
class SubscriptionService {
    private val subscriptions = mutableListOf(
        Subscription(1, "iCloud", 50.0),
        Subscription(2, "iCloud2", 50.0)
    )

    fun getAll(): List<Subscription> = subscriptions

    fun getById(id: Int): Subscription? = subscriptions.find { it.id == id }

    fun add(subscription: Subscription): Subscription {
        subscriptions.add(subscription)
        return subscription
    }

    fun update(
        id: Int,
        subscription: Subscription
    ): Subscription? {
        val index = subscriptions.indexOfFirst { it.id == id }
        if (index == -1) return null
        subscriptions[index] = subscription
        return subscription
    }

    fun delete(id: Int) {
        subscriptions.removeIf { it.id == id }
    }
}