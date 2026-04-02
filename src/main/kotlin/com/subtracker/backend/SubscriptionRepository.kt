package com.subtracker.backend

import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Int>