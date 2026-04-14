package com.subtracker.backend.service

import com.subtracker.backend.dto.PaymentDto
import com.subtracker.backend.dto.toDto
import com.subtracker.backend.model.Payment
import com.subtracker.backend.model.Subscription
import com.subtracker.backend.repository.PaymentRepository
import com.subtracker.backend.repository.SubscriptionRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val subscriptionRepository: SubscriptionRepository
) {
    fun findBySubscriptionId(subscriptionId: Int): List<PaymentDto> = paymentRepository.findBySubscriptionId(subscriptionId).map { it.toDto() }

    fun add(subscriptionId: Int, payment: Payment): PaymentDto? {
        val subscription = subscriptionRepository.findById(subscriptionId).orElse(null)
            ?: return null

        return paymentRepository.save(payment.copy(subscription = subscription)).toDto()
    }

    fun update(subscriptionId: Int, paymentId: Int, payment: Payment): PaymentDto? {
        val subscription = subscriptionRepository.findById(subscriptionId).orElse(null) ?: return null
        val existingPayment = paymentRepository.findById(paymentId).orElse(null) ?: return null
        return paymentRepository.save(existingPayment.copy(
            subscription = subscription,
            amount = payment.amount,
            date = payment.date,
            status = payment.status
        )).toDto()
    }

    fun delete(paymentId: Int) {
        paymentRepository.deleteById(paymentId)
    }
}