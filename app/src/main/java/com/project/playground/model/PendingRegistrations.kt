package com.project.playground.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.IdentityHashMap

@Entity
data class PendingRegistrations(
    val playerId : Int,
    val eventId : Int,
    @PrimaryKey(autoGenerate = true)
    val id : Int=0
)
