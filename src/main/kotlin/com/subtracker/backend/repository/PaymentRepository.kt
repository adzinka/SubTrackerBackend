package com.subtracker.backend.repository

import com.subtracker.backend.model.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Int> {
    fun findBySubscriptionId(subscriptionId: Int): List<Payment>
}