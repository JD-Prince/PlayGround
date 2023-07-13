package com.project.playground.applicationmanager

import android.content.Context
import androidx.room.Room
import com.project.playground.model.Player
import com.project.playground.applicationmanager.database.Database
import com.project.playground.applicationmanager.database.dao.ActivityDao
import com.project.playground.applicationmanager.database.dao.RegistrationDao
import com.project.playground.applicationmanager.database.dao.UserDao

private object DatabaseHandler : DaoProvider,CurrentUserHandler
{
    private var currentUser : Player?=null

    @Volatile
    private lateinit var INSTANCE : Database

    override fun getCurrentPlayerId(): Int?
    {
        currentUser?.let{return it.playerId }
        return null
    }
    override fun getCurrentUser() = currentUser

    override fun setCurrentUser(user: Player)
    {
        currentUser = user
    }

    override fun logout()
    {
        currentUser =null
    }

    override fun getUserDao():UserDao
    {

        println("$INSTANCE something")

        return INSTANCE.userDao()

    }

    override fun getActivityDao():ActivityDao
    {
        return INSTANCE.activityDao()
    }

    override fun getRegistrationDao():RegistrationDao
    {
        return INSTANCE.registrationDao()
    }

    fun buildDatabase(context: Context)
    {
        if (!DatabaseHandler::INSTANCE.isInitialized) {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "application_database"
                ).build()
                INSTANCE = instance

            }
        }
    }
}

object DatabaseBuilder
{
    fun buildDataBase(context :Context){
        DatabaseHandler.buildDatabase(context)
    }
}

object DatabaseProvider
{
    fun getDoaHandler() : DaoProvider{
        return DatabaseHandler
    }
    fun getUserHandler(): CurrentUserHandler{
        return DatabaseHandler
    }
}



