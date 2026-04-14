package com.subtracker.backend

import com.subtracker.backend.model.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Int>