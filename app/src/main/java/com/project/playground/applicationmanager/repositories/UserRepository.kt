package com.project.playground.applicationmanager.repositories

import androidx.lifecycle.LiveData
import com.project.playground.model.Player
import com.project.playground.applicationmanager.AuthRepository
import com.project.playground.applicationmanager.CurrentUserHandler
import com.project.playground.applicationmanager.ProfileHandler
import com.project.playground.applicationmanager.SportsActivityHostProvider
import com.project.playground.applicationmanager.UserRepository
import com.project.playground.applicationmanager.database.dao.UserDao
import com.project.playground.enums.AuthenticationStatements
import com.project.playground.enums.RegistrationStatements
import com.project.playground.model.relations.PlayersWithEnrolledActivities
import com.project.playground.enums.EditProfileStatements
import com.project.playground.view.mainscreen.myground.EnrolledGamesListProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//todo check modularity

class UserRepository (private val userDao: UserDao,private val userHandler: CurrentUserHandler) : AuthRepository,UserRepository,
    SportsActivityHostProvider,EnrolledGamesListProvider,ProfileHandler{
    override suspend fun registerNewUser(user : Player):RegistrationStatements{
        val existingUser : Player? =userDao.getUser(user.userName)
        if(existingUser==null){
            userDao.upsertUser(user)
            val insertedUser = withContext(Dispatchers.IO){
                userDao.getUser(user.userName)
            }
            insertedUser?.let{
                userHandler.setCurrentUser(insertedUser)
            }
            return RegistrationStatements.REGISTRATION_SUCCESSFUL
        }
        return RegistrationStatements.USER_ALREADY_EXIST
    }

    override fun getPlayer(playerId : Int) : LiveData<Player>{
        return userDao.getUserAsLiveData(playerId)
    }

    override suspend fun login(username: String,password : String) : AuthenticationStatements{

        val userObj: Player? = userDao.getUser(username)
        userObj?.let {
            if(it.password==password) {
                userHandler.setCurrentUser(it)
                return AuthenticationStatements.VALID_USER
            }
            return AuthenticationStatements.INVALID_USER
        }
        return AuthenticationStatements.USER_NOT_FOUND

    }

    override suspend fun setCurrentUser(username : String):Boolean{
        userDao.getUser(username)?.let{
            userHandler.setCurrentUser(it)
            return true
        }
        throw IllegalArgumentException("User Not Found")
    }

    override fun getCurrentUserObj(): LiveData<Player> {
        userHandler.getCurrentPlayerId()?.let {
           return userDao.getUserAsLiveData(it)
        }
        throw IllegalArgumentException("Authentication Failed")
    }

    override suspend fun logoutUser(){
        userHandler.logout()
    }



    override suspend fun update(
        imageArray: ByteArray?,
        bio: String?,
        alias: String,
        username: String,
        phoneNumber: String,
        player : Player
    ) :EditProfileStatements{
        val existingUser : Player? = userDao.getUser(username)
        if((existingUser != null && existingUser.playerId==player.playerId) || existingUser==null){
            player.apply {
                this.alias=alias
                this.bio=bio?:""
                profilePicture=imageArray
                this.userName=username
                this.phoneNumber=phoneNumber
                println(this)
            }
            userDao.updatePlayer(player)
            userHandler.setCurrentUser(player)
            return EditProfileStatements.UPDATED_SUCCESSFUL
        }
        return EditProfileStatements.USERNAME_IS_NOT_AVAILABLE
    }

    override suspend fun update(player: Player) {
        userDao.upsertUser(player)
    }


    override suspend fun getHost(id : Int) :Player?{
        return userDao.getUser(id)
    }
     override fun getMyEnrolledGames():LiveData<PlayersWithEnrolledActivities>{
         userHandler.getCurrentPlayerId()?.let {
             return userDao.getMyEnrolledGames(it)
         }
         throw IllegalArgumentException("Authentication Failed")

     }

}


