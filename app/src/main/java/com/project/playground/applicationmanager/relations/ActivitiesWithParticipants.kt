package com.project.playground.applicationmanager.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.project.playground.model.ParticipantsRegister
import com.project.playground.model.Player
import com.project.playground.model.SportActivity

data class ActivitiesWithParticipants(
    @Embedded
    val activity: SportActivity,

    @Relation(
        parentColumn = "eventId",
        entityColumn = "playerId",
        associateBy = Junction(ParticipantsRegister::class)
    )

    val playersList : MutableList<Player>
)
