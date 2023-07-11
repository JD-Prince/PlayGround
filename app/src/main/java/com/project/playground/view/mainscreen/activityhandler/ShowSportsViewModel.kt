package com.project.playground.view.mainscreen.activityhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.model.relations.ActivitiesWithParticipants
import com.project.playground.model.Player
import com.project.playground.applicationmanager.EventRepo
import com.project.playground.applicationmanager.SportsActivityHostProvider
import com.project.playground.applicationmanager.repositories.RegistrationRepository
import com.project.playground.model.SportActivity
import kotlinx.coroutines.launch

class ShowSportsViewModel(private val eventRepo : EventRepo,private val hostProvider : SportsActivityHostProvider,private val registrationRepo : RegistrationRepository,val currentUserId : Int?) : ViewModel() {

    init {

        if(currentUserId==null)
            throw IllegalArgumentException("Invalid approach")
    }
    lateinit var selectedEvent : LiveData<ActivitiesWithParticipants>
    private  var _eventHost = MutableLiveData<Player>()
    val eventHostasLiveData : LiveData<Player>
        get()=_eventHost
    private var currentEventId : Int = 0
    private val _registrationStatus = MutableLiveData<Boolean?>()
    val registrationStatus:LiveData<Boolean?> = _registrationStatus


    fun setEventData(eventId: Int){
        currentEventId=eventId
        if(eventId == 0 ) throw IllegalArgumentException("Process cannot be invoked. invalid ID")
        selectedEvent = eventRepo.getEventWithParticipants(eventId)

    }

    fun setHost(host: Int) {
        viewModelScope.launch {
            hostProvider.getHost(host)?.let {
                    host->
                _eventHost.value= host
                return@launch
            }
            throw IllegalArgumentException("Host ID not found")
        }

    }

    fun requestRegistration() {
        selectedEvent.value?.let {
            viewModelScope.launch {
                registrationRepo.requestRegistration(it.activity,it.activity.host)
            }
            return@requestRegistration
        }
        throw NullPointerException("Unable to proceed the request")
    }

    fun deleteEvent() {
        if(selectedEvent.value==null){
            throw NullPointerException("Unable to find the event")
        }
        viewModelScope.launch {
            selectedEvent.value!!.let {
                eventRepo.deleteEvent(it.activity.eventId)
                registrationRepo.notifyUser(it.playersList,it.activity.eventId,it.activity.title)
            }
        }

    }

    fun intiateRegistrationStatus() {
        if(currentEventId==0){
            throw RuntimeException("Current event ID is not initialized")
        }
        viewModelScope.launch {
            _registrationStatus.value = registrationRepo.isRegistrationCompleted(currentEventId)
        }
    }

    fun updateGame(game: SportActivity) {
        viewModelScope.launch {
            eventRepo.updateGame(game)
            if(game.requiredPlayers==game.enrolledPlayers)registrationRepo.removePendingRegistration(game)
        }
    }

    fun leaveGame() {
        if(this.currentEventId == 0 ) throw IllegalArgumentException("Unable to proceed with the request")
        viewModelScope.launch {
            registrationRepo.leaveGame(selectedEvent.value!!.activity)
            //todo check non null assertion
        }
    }


}