package com.project.playground.applicationmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.project.playground.model.relations.RequestWithActivityAndPlayer
import com.project.playground.model.Notification
import com.project.playground.model.ParticipantsRegister
import com.project.playground.model.PendingRegistrations
import com.project.playground.model.Player
import com.project.playground.model.SportActivity

@Dao
interface RegistrationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRequest(request: Notification.Request)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotice(notice: Notification.Notice)

    @Transaction
    @Query("select * from request where receiverId = :userId order by id desc")
    fun getAllRequestForThePlayer(userId: Int): LiveData<List<RequestWithActivityAndPlayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendingRegistration(pendingRegistrations: PendingRegistrations)

    @Query("DELETE FROM PendingRegistrations where playerId= :playerId AND eventId = :eventId")
    suspend fun removePendingRegistration(playerId: Int, eventId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerPlayerForTheEvent(register: ParticipantsRegister)

    @Query("Delete from request where id = :requestId")
    suspend fun removeRequest(requestId: Int)

    @Query("Select * from notice where receiverId = :userID order by id desc")
    fun getAllNoticeForThePlayer(userID: Int): LiveData<List<Notification.Notice>>

    @Query("Delete from Participantsregister where eventId = :eventId")
    suspend fun removeRegistration(eventId: Int)

    @Query("select * from ParticipantsRegister where playerId = :userId")
    suspend fun getEnrolledEventForThePlayer(userId: Int): List<ParticipantsRegister>

    @Query("select * from Pendingregistrations where playerId=:userId")
    suspend fun getPendingRegistrationsForThePlayer(userId: Int): List<PendingRegistrations>

    @Query("Delete from request where activityId = :eventId")
    suspend fun removeRequestFortheEvent(eventId: Int)

    @Update
    fun updatePlayer(player: Player)

    @Query("select * from request where activityId == :eventId")
    suspend fun getAllRequestForTheEvent(eventId: Int): List<Notification.Request>

    @Query("delete from request where activityId == :eventId")
    suspend fun removeAllRequestForTheGame(eventId: Int)

    @Query("delete from pendingregistrations where eventId == :eventId")
    suspend fun removeAllPendingRegistration(eventId: Int)

    @Query("delete from ParticipantsRegister where eventId = :gameId and playerId = :playerId ")
    suspend fun removePlayer(playerId: Int,gameId : Int)

}