package com.project.playground.view.mainscreen.activityhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.model.SportActivity
import com.project.playground.applicationmanager.repositories.ActivityRepository
import com.project.playground.enums.Location
import com.project.playground.enums.Sports
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewSportsActivityCreaterViewModel(private val activityRepository: ActivityRepository) : ViewModel() {

    private val _isRegistrationSuccessful = MutableLiveData<Boolean>()
    val isRegistrationSuccessful : LiveData<Boolean>
        get()=_isRegistrationSuccessful

    //todo save the value while orientation change

    fun addActivityToMyDatabase(
        title: String,
        sports: Sports,
        description: String,
        location: Location,
        otherInstructions: String,
        requiredPlayers: Int,
        date: String,
        time: String,
        imageData: ByteArray?
    ) {
        val activity=SportActivity(
            title = title,
            sports = sports,
            description = description,
            location = location,
            otherInstructions = otherInstructions,
            requiredPlayers = requiredPlayers,
            date=date,
            time=time,
            thumbnail = imageData

        )
        viewModelScope.launch{
            activityRepository.addActivitytoDatabase(activity)
            withContext(Dispatchers.Main){
                _isRegistrationSuccessful.value=true
            }
        }


    }
}