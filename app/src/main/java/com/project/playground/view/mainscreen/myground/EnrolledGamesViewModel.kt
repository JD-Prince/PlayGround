package com.project.playground.view.mainscreen.myground

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.playground.model.Player
import com.project.playground.model.relations.PlayersWithEnrolledActivities
import com.project.playground.model.SportActivity

class EnrolledGamesViewModel(private val userRepo : EnrolledGamesListProvider) : ViewModel() {
    lateinit var gameList: MutableList<SportActivity>
    lateinit var player: Player

    fun getEnrolledGameList():LiveData<PlayersWithEnrolledActivities> {
        return userRepo.getMyEnrolledGames()
    }

}