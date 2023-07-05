package com.project.playground.view.auth.login

import android.content.SharedPreferences.Editor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.playground.applicationmanager.repositories.UserRepository
import com.project.playground.enums.AuthenticationStatements
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository : UserRepository) : ViewModel(){

//    private var currentUser = MutableLiveData<User>()
    var username : String?  = null


    private val _userNotFound = MutableLiveData<Boolean>()
    val userNotFound : LiveData<Boolean>
        get() = _userNotFound
    private val _isEmptyField = MutableLiveData<Boolean>()
    val isEmptyField : LiveData<Boolean>
        get() = _isEmptyField
    private val _isValidUser = MutableLiveData<Boolean>()
    val isValidUser : LiveData<Boolean>
        get() = _isValidUser




    fun login(username : String, password :String,editor: Editor){


        if(username.isEmpty() || password.isEmpty() )
            _isEmptyField.value=true
        else
            viewModelScope.launch{
                when(userRepository.login(username, password)){
                    AuthenticationStatements.USER_NOT_FOUND->_userNotFound.value=true
                    AuthenticationStatements.VALID_USER->{
                        _isValidUser.value=true
                        editor.apply {
                            putString("USERNAME",username)
                            apply()
                        }
                    }
                    AuthenticationStatements.INVALID_USER->{
                        _isValidUser.value=false
                    }
                }

            }
    }
}