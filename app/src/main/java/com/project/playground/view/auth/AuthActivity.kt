package com.project.playground.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import com.project.playground.R
import com.project.playground.applicationmanager.DatabaseBuilder
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivitySigninSignupBinding
import com.project.playground.view.mainscreen.HomeMainActivity
import com.project.playground.view.auth.login.LoginFragment
import com.project.playground.view.auth.signup.SignUpFragment

class AuthActivity : AppCompatActivity() {


    private var _binding : ActivitySigninSignupBinding?=null
    private val binding : ActivitySigninSignupBinding
        get() = _binding!!
    private val viewModel : AuthViewModel by viewModels {
        ViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivitySigninSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DatabaseBuilder.buildDataBase(applicationContext.applicationContext)

        val loggedUserName = getSharedPreferences("LOGIN_PREF", MODE_PRIVATE).getString("USERNAME","")

        if(loggedUserName!!.isNotEmpty())viewModel.setCurrentUser(loggedUserName)
        val content: View = findViewById(android.R.id.content)
        val preDrawListener : ViewTreeObserver.OnPreDrawListener

        viewModel.isLoggedUser.observe(this) { isValid ->
            if (isValid) {
                println("LAALALALAALLALA")
                startActivity(Intent(baseContext, HomeMainActivity::class.java))
                finish()
            }
        }


        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {

                    println(loggedUserName + "TaTATATAta")
                    if(loggedUserName.isEmpty()){
                        println("validated")
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        return true
                    }
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    return false
                }
            }
        )

        initiateAuthentication()
//        if(loggedUser != ""){
//            DatabaseProvider.apply {
//
//                setCurrentUser(getDatabase(applicationContext.applicationContext).userDao().getUser(loggedUser))
//            }
//            startActivity(Intent(this,HomeActivity :: class.java))
//            finish()
//        }




    }

    private fun initiateAuthentication() {
        supportFragmentManager.beginTransaction().replace(binding.signupSigninDisplay.id,LoginFragment(),"LOGIN_FRAG").commit()

        binding.switchText.apply {
            setOnClickListener{
                if(supportFragmentManager.findFragmentByTag("LOGIN_FRAG")?.isVisible == true){
                    supportFragmentManager.beginTransaction().replace(binding.signupSigninDisplay.id,SignUpFragment(),"SIGNUP_FRAG").commit()
                    binding.switchCaption.text=getText(R.string.already_have_an_account)
                    text=getText(R.string.login)

                }
                else{
                    supportFragmentManager.beginTransaction().replace(binding.signupSigninDisplay.id,LoginFragment(),"LOGIN_FRAG").commit()
                    binding.switchCaption.text=getText(R.string.don_t_have_an_account)
                    text=getText(R.string.register)
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    //TODO update the theme


}