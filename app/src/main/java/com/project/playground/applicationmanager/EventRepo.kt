package com.project.playground.applicationmanager

import androidx.lifecycle.LiveData
import com.project.playground.model.relations.ActivitiesWithParticipants
import com.project.playground.model.SportActivity

interface EventRepo {
    fun getEventById(eventId : Int): LiveData<SportActivity>
    fun getEventWithParticipants(eventId: Int): LiveData<ActivitiesWithParticipants>
    suspend fun deleteEvent(eventId: Int)
    suspend fun updateGame(game: SportActivity)
}