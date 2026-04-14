package com.subtracker.backend

import com.subtracker.backend.model.Subscription
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
    fun getSubscriptions(): List<Subscription> = subscriptionService.getAll()

    @GetMapping("/subscriptions/{id}")
    fun getSubscriptionById(@PathVariable id: Int): Subscription? = subscriptionService.getById(id)

    @PostMapping("/subscriptions")
    fun addSubscription(@RequestBody subscription: Subscription): Subscription = subscriptionService.add(subscription)

    @PutMapping("/subscriptions/{id}")
    fun updateSubscription(
        @PathVariable id: Int,
        @RequestBody subscription: Subscription
    ): Subscription? = subscriptionService.update(id, subscription)

    @DeleteMapping("/subscriptions/{id}")
    fun deleteSubscription(@PathVariable id: Int) {
        subscriptionService.delete(id)
    }
}
