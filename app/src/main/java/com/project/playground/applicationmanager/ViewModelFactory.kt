package com.project.playground.applicationmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.playground.applicationmanager.repositories.ActivityRepository
import com.project.playground.applicationmanager.repositories.RegistrationRepository
import com.project.playground.applicationmanager.repositories.UserRepository
import com.project.playground.view.mainscreen.HomeMainViewModel
import com.project.playground.view.mainscreen.myground.MyActivitiesViewModel
import com.project.playground.view.mainscreen.activityhandler.NewSportsActivityCreaterViewModel
import com.project.playground.view.mainscreen.home.RecommendedActivitiesViewModel
import com.project.playground.view.auth.AuthViewModel
import com.project.playground.view.auth.login.LoginViewModel
import com.project.playground.view.auth.signup.SignUpViewModel
import com.project.playground.view.mainscreen.activityhandler.ShowSportsViewModel
import com.project.playground.view.mainscreen.myground.EnrolledGamesViewModel
import com.project.playground.view.mainscreen.notifications.NoticeListViewModel
import com.project.playground.view.mainscreen.notifications.RequestHandlerViewModel
import com.project.playground.view.mainscreen.profile.EditProfileViewModel
import com.project.playground.view.mainscreen.profile.PasswordHandlerViewModel
import com.project.playground.view.mainscreen.profile.ProfileViewModel
import com.project.playground.view.mainscreen.profile.ViewProfileViewModel

object ViewModelFactory : ViewModelProvider.Factory {


//    private val daoProvider = DatabaseProvider.getDoaHandler()
//    private val userHandler = DatabaseProvider.getUserHandler()
//    private val userRepository = UserRepository(daoProvider.getUserDao(), userHandler)
//    private val activityRepository = ActivityRepository(DatabaseProvider.getDoaHandler().getActivityDao(), DatabaseProvider.getUserHandler())


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) ->
                SignUpViewModel(UserRepository(DatabaseProvider.getDoaHandler().getUserDao(), DatabaseProvider.getUserHandler())) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(UserRepository(DatabaseProvider.getDoaHandler().getUserDao(), DatabaseProvider.getUserHandler())) as T
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(UserRepository(DatabaseProvider.getDoaHandler().getUserDao(), DatabaseProvider.getUserHandler())) as T
            modelClass.isAssignableFrom(HomeMainViewModel::class.java) ->
                HomeMainViewModel(UserRepository(DatabaseProvider.getDoaHandler().getUserDao(), DatabaseProvider.getUserHandler())) as T
            modelClass.isAssignableFrom(MyActivitiesViewModel::class.java) ->
                MyActivitiesViewModel(ActivityRepository(DatabaseProvider.getDoaHandler().getActivityDao(), DatabaseProvider.getUserHandler(),DatabaseProvider.getDoaHandler().getUserDao())) as T
            modelClass.isAssignableFrom(RecommendedActivitiesViewModel::class.java) ->
                RecommendedActivitiesViewModel(ActivityRepository(DatabaseProvider.getDoaHandler().getActivityDao(), DatabaseProvider.getUserHandler(),DatabaseProvider.getDoaHandler().getUserDao())) as T
            modelClass.isAssignableFrom(NewSportsActivityCreaterViewModel::class.java) ->

                NewSportsActivityCreaterViewModel(

                    ActivityRepository(
                        DatabaseProvider.getDoaHandler().getActivityDao(),
                        DatabaseProvider.getUserHandler(),
                        DatabaseProvider.getDoaHandler().getUserDao()
                    )

                ) as T
            modelClass.isAssignableFrom(ShowSportsViewModel::class.java)->



                ShowSportsViewModel(

                    ActivityRepository(
                        DatabaseProvider.getDoaHandler().getActivityDao(),
                        DatabaseProvider.getUserHandler(),
                        DatabaseProvider.getDoaHandler().getUserDao()
                    ),

                    UserRepository(
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getUserHandler()
                    ),

                    RegistrationRepository(
                        DatabaseProvider.getUserHandler(),
                        DatabaseProvider.getDoaHandler().getRegistrationDao(),
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getDoaHandler().getActivityDao()
                    ),
                    DatabaseProvider.getUserHandler().getCurrentPlayerId()!!

                ) as T
            modelClass.isAssignableFrom(RequestHandlerViewModel::class.java)->
                RequestHandlerViewModel(
                    RegistrationRepository(
                        DatabaseProvider.getUserHandler(),
                        DatabaseProvider.getDoaHandler().getRegistrationDao(),
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getDoaHandler().getActivityDao()
                    )

                ) as T
            modelClass.isAssignableFrom(EnrolledGamesViewModel::class.java)->
                EnrolledGamesViewModel(
                    UserRepository(
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getUserHandler()
                    )
                ) as T

            modelClass.isAssignableFrom(NoticeListViewModel::class.java)->
                NoticeListViewModel(
                    RegistrationRepository(
                        DatabaseProvider.getUserHandler(),
                        DatabaseProvider.getDoaHandler().getRegistrationDao(),
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getDoaHandler().getActivityDao()
                    )
                ) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->
                ProfileViewModel(
                    UserRepository(
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getUserHandler()
                    )
                )as T
            modelClass.isAssignableFrom(EditProfileViewModel::class.java)->
                EditProfileViewModel(
                    UserRepository(
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getUserHandler()
                    )
                ) as T
            modelClass.isAssignableFrom(PasswordHandlerViewModel::class.java)->{
                PasswordHandlerViewModel(
                    UserRepository(
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getUserHandler()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(ViewProfileViewModel::class.java)->
            {
                ViewProfileViewModel(
                    UserRepository(
                        DatabaseProvider.getDoaHandler().getUserDao(),
                        DatabaseProvider.getUserHandler()
                    )
                ) as T
            }
            else ->
                throw IllegalArgumentException("ViewModel class Not Found")
        }
    }
}