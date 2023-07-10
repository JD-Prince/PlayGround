package com.project.playground.view.mainscreen.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.model.relations.RequestWithActivityAndPlayer
import com.project.playground.applicationmanager.repositories.RegistrationRepository
import com.project.playground.applicationmanager.repositories.UserRepository
import com.project.playground.model.Notification
import com.project.playground.model.SportActivity
import kotlinx.coroutines.launch

class RequestHandlerViewModel(private val registrationRepo : RegistrationRepository) : ViewModel() {
    fun getRequestData(): LiveData<List<RequestWithActivityAndPlayer>> {
        return registrationRepo.getAllRequestForTheUser()
    }

    fun approveRequest(request: Notification.Request,event : SportActivity) {
        viewModelScope.launch {
            registrationRepo.approveRequest(request,event)
        }
    }

    fun declineRequest(request: Notification.Request,eventTitle: String) {
        viewModelScope.launch {
            registrationRepo.declineRequest(request,eventTitle)
        }
    }

}