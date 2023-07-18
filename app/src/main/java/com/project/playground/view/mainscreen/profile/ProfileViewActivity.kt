package com.project.playground.view.mainscreen.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.project.playground.R
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivityProfileViewBinding
import com.project.playground.util.CacheUtils
import com.project.playground.view.util.ImageViewActivity
import java.io.ByteArrayOutputStream

class ProfileViewActivity : AppCompatActivity() {
    private var _binding : ActivityProfileViewBinding? = null
    private val binding : ActivityProfileViewBinding
        get()=_binding!!
    private val viewModel : ViewProfileViewModel by viewModels {
        ViewModelFactory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityProfileViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val playerId : Int = intent.getIntExtra("PLAYER_ID",0)
        if(playerId == 0) throw IllegalArgumentException("Invalid user")
        viewModel.getUserData(playerId).observe(this){
            player->

            binding.apply {
                setSupportActionBar(this.toolbar.activityToolbar)
                supportActionBar?.apply {
                    //todo animate to roll
                    title = player.userName
                    setDisplayHomeAsUpEnabled(true)
                }
                player.profilePicture?.let {
                    image->
                    profilePic.setImageBitmap(
                        BitmapFactory.decodeByteArray(
                            image,
                            0,
                            image.size
                        )
                    )
                }
                profilePic.setOnClickListener {
                    val imageViewIntent = Intent(this@ProfileViewActivity,ImageViewActivity::class.java)

                    val imageKey = "IMAGE_DATA"
                    player.profilePicture?.let {
                        CacheUtils.saveBitmapToCache(imageKey,BitmapFactory.decodeByteArray(
                            it,
                            0,
                            it.size
                        ))
                        imageViewIntent.putExtra("IMG_KEY",imageKey)
                    }
                    startActivity(imageViewIntent)
                }

                alias.text=player.alias
                bio.text=player.bio
                eventPts.text=player.totalEvents.toString()
                pointsPts.text=player.points.toString()
                organizingGame.text=player.organizingEventCount.toString()
                enrolledGames.text=player.enrolledEventCount.toString()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}