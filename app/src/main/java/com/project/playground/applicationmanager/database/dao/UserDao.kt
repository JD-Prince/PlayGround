package com.project.playground.applicationmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.project.playground.model.Player
import com.project.playground.applicationmanager.relations.PlayersWithEnrolledActivities

@Dao
interface UserDao  {
    @Upsert
    suspend fun upsertUser(user : Player)

    @Query("SELECT * FROM user_data_table WHERE username=:username")
    suspend fun getUser(username: String ):Player?

    @Query("Select * From user_data_table where playerId= :userId")
    fun getUserAsLiveData(userId : Int) : LiveData<Player>

    @Query("Select * from user_data_table where playerId = :userId")
    suspend fun getUser(userId : Int):Player?
    @Transaction
    @Query("select * from user_data_table where playerId = :currentUserID")
    fun getMyEnrolledGames(currentUserID: Int): LiveData<PlayersWithEnrolledActivities>

    @Update
    suspend fun updatePlayer(player: Player)



}