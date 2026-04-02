package com.subtracker.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController {
    @GetMapping("/subscriptions")
    fun getSubscriptions(): String = "subscriptions"
}