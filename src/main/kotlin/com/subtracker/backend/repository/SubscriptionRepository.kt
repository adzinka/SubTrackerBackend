package com.subtracker.backend.repository

import com.subtracker.backend.model.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Int>