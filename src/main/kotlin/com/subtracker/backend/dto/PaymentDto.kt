package com.subtracker.backend.dto

import com.subtracker.backend.model.PaymentStatus

data class PaymentDto(
    val id: Int,
    val subscriptionId: Int,
    val date: String,
    val amount: Int,
    val currency: String,
    val status: PaymentStatus
)
