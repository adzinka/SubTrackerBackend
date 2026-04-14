package com.subtracker.backend.dto

import com.subtracker.backend.model.Payment
import com.subtracker.backend.model.PaymentStatus

data class PaymentDto(
    val id: Int,
    val subscriptionId: Int,
    val date: String,
    val amount: Int,
    val currency: String,
    val status: PaymentStatus
)

fun Payment.toDto() = PaymentDto(
    id = id,
    subscriptionId = subscription?.id ?: 0,
    date = date,
    amount = amount,
    currency = currency,
    status = status
)
