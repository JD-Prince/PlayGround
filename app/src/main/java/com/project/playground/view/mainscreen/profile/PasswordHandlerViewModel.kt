package com.project.playground.view.mainscreen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.applicationmanager.repositories.UserRepository
import com.project.playground.enums.PasswordCorrectionStatus
import com.project.playground.model.Player
import kotlinx.coroutines.launch

class PasswordHandlerViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _result = MutableLiveData<PasswordCorrectionStatus>()
    val result : LiveData<PasswordCorrectionStatus>
        get()=_result
    var currentUser : Player?=null
    fun loadUserData():LiveData<Player>{
        return userRepository.getCurrentUserObj()
    }

    fun checkData(currentPassword: String, newPassword: String, reEnteredPassword: String) {
        println("$currentPassword   $newPassword   $reEnteredPassword"  )
        if(currentPassword.isNullOrEmpty() || newPassword.isNullOrEmpty() || reEnteredPassword.isNullOrEmpty()){
            _result.value=PasswordCorrectionStatus.FIELD_CANNOT_BE_EMPTY
            return
        }
        if(currentPassword != currentUser!!.password){
            _result.value=PasswordCorrectionStatus.INCORRECT_PASSWORD
            return
        }
        val passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
        if(!newPassword.matches(passwordRegex)){
            _result.value=PasswordCorrectionStatus.REGEX_MISMATCH
            return
        }
        if(newPassword!=reEnteredPassword){
            _result.value=PasswordCorrectionStatus.PASSWORD_MISMATCH
            return
        }
        currentUser!!.password=newPassword
        viewModelScope.launch {
            userRepository.update(currentUser!!)
        }
        _result.value=PasswordCorrectionStatus.SUCCESSFUL
    }

}