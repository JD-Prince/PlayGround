package com.project.playground.applicationmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.project.playground.model.relations.ActivitiesWithParticipants
import com.project.playground.model.SportActivity

@Dao
interface ActivityDao {

    @Query("SELECT * FROM sport_activity_table where host <> :currentUserID and number_of_players_enrolled != number_of_players_required order by eventId desc")
    fun getRecommendedActivities(currentUserID : Int) : LiveData<List<SportActivity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: SportActivity)


    @Query("SELECT * FROM sport_activity_table where host <> :currentUserID LIMIT :pageSize OFFSET :offset")
    fun getItems(offset: Int, pageSize: Int,currentUserID: Int): LiveData<List<SportActivity>>


    @Query("select * from sport_activity_table where host = :currentUserID order by eventId desc")
    fun getMyOrganizingGames(currentUserID: Int):LiveData<List<SportActivity>>

    @Query("select * from sport_activity_table where eventId = :eventId")
    fun getEventByIdAsLiveData(eventId : Int):LiveData<SportActivity>

    @Query("select * from sport_activity_table where eventId = :eventId")
    suspend fun getEventById(eventId : Int):SportActivity

    @Transaction
    @Query("select * from sport_activity_table where eventId = :eventId")
    fun getEventWithParticipants(eventId : Int) : LiveData<ActivitiesWithParticipants>

    @Query("Delete from sport_activity_table where eventId=:eventId")
    suspend fun deleteEvent(eventId: Int)

    @Update
    suspend fun updateGame(game: SportActivity)

}