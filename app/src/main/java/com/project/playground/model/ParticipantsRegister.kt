package com.project.playground.model

import androidx.room.Entity

@Entity(primaryKeys = ["playerId","eventId"])
data class ParticipantsRegister(
     val playerId : Int,
     val eventId : Int
)
