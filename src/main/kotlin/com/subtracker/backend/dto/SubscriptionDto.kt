package com.subtracker.backend.dto

import com.subtracker.backend.model.BillingPeriod
import com.subtracker.backend.model.Category
import com.subtracker.backend.model.Subscription
import com.subtracker.backend.model.SubscriptionStatus

data class SubscriptionDto(
    val id: Int,
    val name: String,
    val category: Category,
    val nextPaymentDate: String,
    val price: Double,
    val currency: String,
    val billingPeriod: BillingPeriod,
    val reminderDays: Int?,
    val status: SubscriptionStatus,
    val notes: String?
)

fun Subscription.toDto() = SubscriptionDto(
    id = id,
    name = name,
    category = category,
    nextPaymentDate = nextPaymentDate,
    price = price,
    currency = currency,
    billingPeriod = billingPeriod,
    reminderDays = reminderDays,
    status = status,
    notes = notes
)