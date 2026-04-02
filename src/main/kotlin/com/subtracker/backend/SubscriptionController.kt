package com.subtracker.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController(private val subscriptionService: SubscriptionService) {
    @GetMapping("/subscriptions")
    fun getSubscriptions(): List<Subscription> = subscriptionService.getAll()

    @GetMapping("/subscriptions/{id}")
    fun getSubscriptionById(@PathVariable id: Int): Subscription? = subscriptionService.getById(id)

    @PostMapping("/subscriptions")
    fun addSubscription(@RequestBody subscription: Subscription): Subscription = subscriptionService.add(subscription)
}
