package com.project.playground.applicationmanager

import androidx.lifecycle.LiveData
import com.project.playground.enums.EditProfileStatements
import com.project.playground.model.Player

interface UserRepository {
    fun getCurrentUserObj() : LiveData<Player>
    suspend fun logoutUser()
    suspend fun update(imageArray: ByteArray?, bio: String?, alias: String, username: String, phoneNumber: String,player: Player): EditProfileStatements
    suspend fun update(player: Player)


}