package com.subtracker.backend.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "payments")
data class Payment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val date: String = "",
    val amount: Int = 0,
    val currency: String = "",
    @Enumerated(EnumType.STRING)
    val status: PaymentStatus = PaymentStatus.PLANNED,

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    val subscription: Subscription,
)

enum class PaymentStatus { PLANNED, PAID }

