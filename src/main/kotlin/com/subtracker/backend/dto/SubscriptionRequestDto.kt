package com.subtracker.backend.dto

import com.subtracker.backend.model.BillingPeriod
import com.subtracker.backend.model.Category
import com.subtracker.backend.model.Subscription
import com.subtracker.backend.model.SubscriptionStatus
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SubscriptionRequestDto(
    @field:NotBlank val name: String,
    @field:NotNull val category: Category,
    @field:NotBlank val nextPaymentDate: String,
    @field:Min(0) val price: Double,
    val currency: String = "CZK",
    val billingPeriod: BillingPeriod = BillingPeriod.MONTHLY,
    val reminderDays: Int? = null,
    val status: SubscriptionStatus = SubscriptionStatus.ACTIVE,
    val notes: String? = null
)

fun SubscriptionRequestDto.toEntity() = Subscription(
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