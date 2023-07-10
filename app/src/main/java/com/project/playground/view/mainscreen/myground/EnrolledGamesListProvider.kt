package com.project.playground.view.mainscreen.myground

import androidx.lifecycle.LiveData
import com.project.playground.model.relations.PlayersWithEnrolledActivities

interface EnrolledGamesListProvider {

    fun getMyEnrolledGames(): LiveData<PlayersWithEnrolledActivities>
}
