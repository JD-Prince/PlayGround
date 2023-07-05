package com.project.playground.applicationmanager

import com.project.playground.model.Player
import com.project.playground.enums.AuthenticationStatements
import com.project.playground.enums.RegistrationStatements

interface AuthRepository {

    suspend fun registerNewUser(user : Player) : RegistrationStatements

    suspend fun login(username : String,  password : String) : AuthenticationStatements

    suspend fun setCurrentUser(username: String): Boolean

}