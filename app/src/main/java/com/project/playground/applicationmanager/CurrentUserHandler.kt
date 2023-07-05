package com.project.playground.applicationmanager

import com.project.playground.model.Player

interface CurrentUserHandler {
    fun setCurrentUser(user : Player)
    fun getCurrentPlayerId() : Int?
    fun logout()
    fun getCurrentUser(): Player?
}