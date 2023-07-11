package com.project.playground.view.mainscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.project.playground.R
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivityHomeBinding
import com.project.playground.enums.ConfrimationStatus
import com.project.playground.view.mainscreen.profile.ProfileFragment
import com.project.playground.view.auth.AuthActivity
import com.project.playground.view.dialogues.ConfrimationDialogueFragment
import com.project.playground.view.mainscreen.home.RecommendedActivitiesFragment
import com.project.playground.view.mainscreen.myground.MyGroundFragment
import com.project.playground.view.mainscreen.profile.EditProfileActivity
import com.project.playground.view.mainscreen.notifications.NotificationHandlerActivity
import com.project.playground.view.mainscreen.profile.SettingFragment

class HomeMainActivity : AppCompatActivity() {

    val REGISTERED_PLAYER = "REGISTERED_PLAYER"
    val REGISTRATION_PENDING = "REGISTRATION_PENDING"

    private var _binding : ActivityHomeBinding? =null
    private val binding
        get() = _binding!!
    private lateinit var viewModel: HomeMainViewModel

    private val HOME_TAG = "HOME_FRAG"
    private val MY_GROUND_TAG = "MY_GROUND_FRAG"
    private val PROFILE_TAG = "PROFILE_FRAG"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this,ViewModelFactory)[HomeMainViewModel :: class.java]



        _binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(MyGroundFragment(),MY_GROUND_TAG)
        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)



        toolbar.menu.clear()
        toolbar.title="My Ground"
        toolbar.inflateMenu(R.menu.my_ground_menu)
        binding.bottomNavigationForHome.menu[1].isChecked=true

        toolbar.setOnMenuItemClickListener {
            when(it.itemId){

                R.id.notification_ic->{

                    startActivity(Intent(this,NotificationHandlerActivity::class.java))
                }
                R.id.settings->{
                    SettingFragment(object : SettingFragment.AuthenticationController{
                        override fun logout() {
                            logoutUser()
                        }
                    }).show(supportFragmentManager,"SETTING_FRAGMENT")
                }

                else -> {}
            }
            true
        }


        binding.bottomNavigationForHome.setOnItemSelectedListener {
            when(it.itemId){

                R.id.home_ic->{
                    if(supportFragmentManager.findFragmentByTag(HOME_TAG)?.isVisible == true) return@setOnItemSelectedListener true
                    setFragment(RecommendedActivitiesFragment(),HOME_TAG)
                    toolbar.menu.clear()
                    toolbar.title="Playground"
                    toolbar.inflateMenu(R.menu.activity_toolbar_menu_home)
                }

                R.id.my_ground_ic->{
                    if(supportFragmentManager.findFragmentByTag(MY_GROUND_TAG)?.isVisible == true) return@setOnItemSelectedListener true


                    setFragment(MyGroundFragment(),MY_GROUND_TAG)
                    toolbar.title="My Ground"
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.my_ground_menu)
                }

                R.id.profile_ic -> {
                    if(supportFragmentManager.findFragmentByTag(PROFILE_TAG)?.isVisible == true) return@setOnItemSelectedListener true

                    setFragment(ProfileFragment(),PROFILE_TAG)
                    toolbar.menu.clear()
                    toolbar.title="My Profile"
                    toolbar.inflateMenu(R.menu.activity_toolbar_menu_profile)
                }
            }
            true
        }

    }
    override fun onResume() {
        super.onResume()

    }





    private fun setFragment(fragment : Fragment,tag : String){
        supportFragmentManager.beginTransaction().replace(binding.fragmentContainerForHome.id,fragment,tag).commit()
    }
    fun logoutUser(){
        viewModel.logoutUser(this.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE).edit())
    }


    override fun onDestroy() {

        super.onDestroy()
        _binding=null
    }
}