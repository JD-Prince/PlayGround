package com.project.playground.applicationmanager

import androidx.lifecycle.LiveData
import com.project.playground.model.Player

interface ProfileHandler {
    fun getPlayer(playerId: Int): LiveData<Player>
}