package com.project.playground.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.project.playground.model.Notification
import com.project.playground.model.Player
import com.project.playground.model.SportActivity

data class RequestWithActivityAndPlayer(

    @Embedded val request : Notification.Request,

    @Relation(
        parentColumn = "activityId",
        entityColumn = "eventId"
    )

    val activity : SportActivity,

    @Relation(
        parentColumn = "senderId",
        entityColumn = "playerId"
    )

    val player: Player
)
