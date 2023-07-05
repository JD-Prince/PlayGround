package com.project.playground.view.auth.signup

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.applicationmanager.repositories.UserRepository
import com.project.playground.model.Player
import com.project.playground.enums.RegistrationStatements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {


    private val _isExistingUser = MutableLiveData<Boolean>()
    val isExistingUser : LiveData<Boolean>
        get() = _isExistingUser

    private val _successfulRegistration = MutableLiveData<Boolean>()
    val successfulRegistration : LiveData<Boolean>
        get()=_successfulRegistration



    fun registerUser(
        password: String,
        edit: SharedPreferences.Editor,
        userName: String = "",
        phoneNumber: String = "",
        alias: String=""
    ){
        val user = Player(userName = userName, password = password,phoneNumber = phoneNumber,alias =alias)
        viewModelScope.launch(Dispatchers.Main) {
            when(userRepository.registerNewUser(user)){
                RegistrationStatements.USER_ALREADY_EXIST->{
                    _isExistingUser.value=true
                }
                RegistrationStatements.REGISTRATION_SUCCESSFUL->{
                    _successfulRegistration.value=true
                    edit.apply(){
                        putString("USERNAME",userName)
                        apply()
                    }
                }
            }
        }
    }




}