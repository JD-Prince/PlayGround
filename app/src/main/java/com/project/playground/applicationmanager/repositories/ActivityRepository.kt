package com.project.playground.applicationmanager.repositories

import androidx.lifecycle.LiveData
import com.project.playground.model.relations.ActivitiesWithParticipants
import com.project.playground.model.SportActivity
import com.project.playground.applicationmanager.CurrentUserHandler
import com.project.playground.applicationmanager.EventRepo
import com.project.playground.applicationmanager.database.dao.ActivityDao
import com.project.playground.applicationmanager.database.dao.RegistrationDao
import com.project.playground.applicationmanager.database.dao.UserDao

class ActivityRepository(private val activityDao: ActivityDao, private val userHandler: CurrentUserHandler, private val userDao: UserDao) : EventRepo{
    private var currentPage=1
    private var pageSize=15
    fun getAllRecommendedActivities():LiveData<List<SportActivity>>{
        userHandler.getCurrentPlayerId()?.let {
            return activityDao.getRecommendedActivities(it)
        }
        throw IllegalArgumentException("Current User Value is Null")
    }
//     fun loadDataForRecyclerView(currentUserID: Int) : LiveData<List<SportActivity>>{
//         val offset=(currentPage-1)*pageSize
//         currentPage++
//        return dao.getItems(offset, pageSize,currentUserID)
//    }

    fun loadMyActivities():LiveData<List<SportActivity>>
    {
        userHandler.getCurrentPlayerId()?.let {
            return activityDao.getMyOrganizingGames(it)
        }
        throw IllegalArgumentException("Current User Value is Null")
    }

    suspend fun addActivitytoDatabase(activity : SportActivity){
        userHandler.getCurrentPlayerId()?.let {
            activity.host=it
            activityDao.insertActivity(activity)
            userHandler.getCurrentUser()!!.also { player->
                player.points+=20
                player.totalEvents++
                player.organizingEventCount++
                userDao.updatePlayer(player)
                userHandler.setCurrentUser(player)
            }
            return
        }
        throw IllegalArgumentException("Current User Value is Null")

    }

    override fun getEventById(eventId: Int): LiveData<SportActivity> {
        return activityDao.getEventByIdAsLiveData(eventId)
    }

    override  fun getEventWithParticipants(eventId : Int): LiveData<ActivitiesWithParticipants> {
        return activityDao.getEventWithParticipants(eventId)
    }

    override suspend fun deleteEvent(eventId: Int) {
        userHandler.getCurrentUser()?.let {player->
            player.points=(player.points-22).takeIf { player.points>22 }?:0
            player.totalEvents--.takeIf { player.totalEvents>0 }?:0
            player.organizingEventCount--.takeIf { player.organizingEventCount>0 }?:0
            userDao.updatePlayer(player)
            userHandler.setCurrentUser(player)
            activityDao.deleteEvent(eventId)
            return
        }
        throw IllegalArgumentException("Current User Value is Null")


    }

    override suspend fun updateGame(game: SportActivity) {
        activityDao.updateGame(game)
    }


}