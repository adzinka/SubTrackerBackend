package com.subtracker.backend.controller

import com.subtracker.backend.model.Payment
import com.subtracker.backend.model.Subscription
import com.subtracker.backend.service.PaymentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(
    private val paymentService: PaymentService
) {
    @GetMapping("/subscriptions/{subscriptionId}/payments")
    fun getPaymentsBySubscriptionId(@PathVariable subscriptionId: Int) = paymentService.findBySubscriptionId(subscriptionId)

    @PostMapping("/subscriptions/{subscriptionId}/payments")
    fun addPayment(@PathVariable subscriptionId: Int, @RequestBody payment: Payment): Payment? =
        paymentService.add(subscriptionId, payment)

    @PutMapping("/subscriptions/{subscriptionId}/payments/{paymentId}")
    fun updatePayment(
        @PathVariable subscriptionId: Int,
        @PathVariable paymentId: Int,
        @RequestBody payment: Payment
    ): Payment? = paymentService.update(subscriptionId, paymentId, payment)

    @DeleteMapping("/subscriptions/{subscriptionId}/payments/{paymentId}")
    fun deletePayment(@PathVariable subscriptionId: Int, @PathVariable paymentId: Int) {
        paymentService.delete(paymentId)
    }
}