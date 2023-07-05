package com.project.playground.applicationmanager.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.project.playground.model.ParticipantsRegister
import com.project.playground.model.Player
import com.project.playground.model.SportActivity

data class PlayersWithEnrolledActivities(

    @Embedded val player: Player,
    @Relation(
        parentColumn = "playerId",
        entityColumn = "eventId",
        associateBy = Junction(ParticipantsRegister :: class)
    )
    val activitiesList : MutableList<SportActivity>
)
