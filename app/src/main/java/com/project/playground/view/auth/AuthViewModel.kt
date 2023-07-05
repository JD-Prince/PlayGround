package com.project.playground.view.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.applicationmanager.repositories.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository):ViewModel() {

    private val _isLoggedUser = MutableLiveData<Boolean>()
    val isLoggedUser
        get() = _isLoggedUser
    fun setCurrentUser(username: String) {
        viewModelScope.launch {
            val ack=userRepository.setCurrentUser(username)
            _isLoggedUser.value=ack
            //todo this segment crash the application
        }
    }

}