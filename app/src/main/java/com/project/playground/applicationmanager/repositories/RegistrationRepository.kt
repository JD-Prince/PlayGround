package com.project.playground.applicationmanager.repositories

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.project.playground.applicationmanager.CurrentUserHandler
import com.project.playground.applicationmanager.database.dao.ActivityDao
import com.project.playground.applicationmanager.database.dao.RegistrationDao
import com.project.playground.applicationmanager.database.dao.UserDao
import com.project.playground.applicationmanager.relations.RequestWithActivityAndPlayer
import com.project.playground.enums.NoticeTypes
import com.project.playground.model.Notification
import com.project.playground.model.ParticipantsRegister
import com.project.playground.model.PendingRegistrations
import com.project.playground.model.Player
import com.project.playground.model.SportActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class RegistrationRepository(private val userHandler : CurrentUserHandler, private val registrationDao : RegistrationDao, private val userDao : UserDao,private val activityDao: ActivityDao) {

    suspend fun requestRegistration(event: SportActivity, host: Int){
        if(event.enrolledPlayers != event.requiredPlayers) {
            userHandler.getCurrentPlayerId()?.let { currentUserId ->
                val pendingRegistrations =
                    PendingRegistrations(playerId = currentUserId, eventId = event.eventId)
                val request = Notification.Request(
                    senderId = currentUserId,
                    activityId = event.eventId,
                    receiverId = host
                )
                registrationDao.insertPendingRegistration(pendingRegistrations)
                registrationDao.addRequest(request)
                return
            }

            throw IllegalArgumentException("Current User Value is Null")
        }

    }

    fun getAllRequestForTheUser(): LiveData<List<RequestWithActivityAndPlayer>> {
        userHandler.getCurrentPlayerId()?.let {
                currentUserId->

            return registrationDao.getAllRequestForThePlayer(currentUserId)

        }
        throw IllegalArgumentException("Current User Value is Null")
    }

    suspend fun approveRequest(request: Notification.Request, event: SportActivity) {
        if (event.enrolledPlayers < event.requiredPlayers) {
            println("jaekfn $event")
            val playerId = request.senderId
            registrationDao.removePendingRegistration(
                playerId = playerId,
                eventId = request.activityId
            )
            val register = ParticipantsRegister(playerId = playerId, request.activityId)
            registrationDao.removeRequest(requestId = request.id)
            registrationDao.registerPlayerForTheEvent(register)
            userDao.getUser(playerId)?.let { player ->
                player.points += 10
                player.enrolledEventCount++
                userDao.updatePlayer(player)
            }

                event.enrolledPlayers++
                activityDao.updateGame(event)


            registrationDao.addNotice(
                Notification.Notice(
                    request.senderId,
                    request.activityId,
                    NoticeTypes.REQUEST_ACCEPTED,
                    event.title
                )
            )

            if(event.enrolledPlayers==event.requiredPlayers){
                removePendingRegistration(event)
            }
        }
    }
    suspend fun declineRequest(request : Notification.Request,eventTitle : String){
        registrationDao.removeRequest(requestId = request.id)
        registrationDao.addNotice(Notification.Notice(request.senderId,request.activityId,NoticeTypes.REQUEST_DECLINED,eventTitle))
    }

    fun getAllNotices():LiveData<List<Notification.Notice>>{
        userHandler.getCurrentPlayerId()?.let {
                currentUserId->

            return registrationDao.getAllNoticeForThePlayer(currentUserId)

        }
        throw IllegalArgumentException("Current User Value is Null")
    }

    suspend fun notifyUser(playerList: MutableList<Player>, eventId: Int, title: String) {
        registrationDao.removeRequestFortheEvent(eventId).also {
            println("event ID = $eventId")
        }
        playerList.forEach{player->
            registrationDao.addNotice(Notification.Notice(receiverId = player.playerId,eventId,NoticeTypes.EVENT_CANCELLED,title))
            registrationDao.removeRegistration(eventId)
            player.points-=10
            userDao.updatePlayer(player)
        }
    }

    suspend fun isRegistrationCompleted(eventId: Int): Boolean?{
        userHandler.getCurrentPlayerId()?.let{
            userID->
            val gameList = registrationDao.getEnrolledEventForThePlayer(userID)
            gameList.forEach{event->
                if(event.eventId==eventId) return@isRegistrationCompleted true
            }
            val pendingRegistrations=registrationDao.getPendingRegistrationsForThePlayer(userID)
            pendingRegistrations.forEach { pendingProcess ->
                if(pendingProcess.eventId==eventId)
                    return@isRegistrationCompleted false
            }
            return@isRegistrationCompleted null
        }

        throw IllegalArgumentException("Current User Value is Null")
    }

    suspend fun removePendingRegistration(event: SportActivity) {
        val pendingRequestList = registrationDao.getAllRequestForTheEvent(event.eventId)
        println("event_id $pendingRequestList --> $event")
        registrationDao.removeAllRequestForTheGame(event.eventId)
        registrationDao.removeAllPendingRegistration(event.eventId)
        pendingRequestList.forEach { request ->
            registrationDao.addNotice(Notification.Notice(request.senderId,request.activityId,NoticeTypes.EVENT_FILLED,event.title))
        }

    }
}