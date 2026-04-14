package com.subtracker.backend.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "subscriptions")
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String = "",
    @Enumerated(EnumType.STRING)
    val category: Category = Category.STREAMING,
    val nextPaymentDate: String = "",
    val price: Double = 0.0,
    val currency: String = "CZK",
    @Enumerated(EnumType.STRING)
    val billingPeriod: BillingPeriod = BillingPeriod.MONTHLY,
    val reminderDays: Int? = null,
    @Enumerated(EnumType.STRING)
    val status: SubscriptionStatus = SubscriptionStatus.ACTIVE,
    val notes: String? = null,

    @OneToMany(mappedBy = "subscription", cascade = [CascadeType.ALL])
    val payments: List<Payment> = emptyList(),
)