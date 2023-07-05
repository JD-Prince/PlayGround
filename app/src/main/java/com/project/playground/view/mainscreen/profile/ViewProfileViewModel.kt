package com.project.playground.view.mainscreen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.playground.applicationmanager.ProfileHandler
import com.project.playground.model.Player

class ViewProfileViewModel(private val profileHandler : ProfileHandler) : ViewModel(){

    fun getUserData(playerId : Int):LiveData<Player>{
        return profileHandler.getPlayer(playerId)
    }

}