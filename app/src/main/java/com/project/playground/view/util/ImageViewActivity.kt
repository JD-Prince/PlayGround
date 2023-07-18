package com.project.playground.view.util

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LruCache
import android.view.View
import com.project.playground.databinding.ActivityImageViewBinding
import com.project.playground.util.CacheUtils
import com.squareup.picasso.Picasso

class ImageViewActivity : AppCompatActivity() {
    private var _binding : ActivityImageViewBinding?=null
    private val binding : ActivityImageViewBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageKey = intent.getStringExtra("IMG_KEY")
        if(imageKey==null){
            binding.emptyFieldCaption.visibility= View.VISIBLE
        }
        else{
            binding.image.setImageBitmap(
                CacheUtils.getBitmapFromCache(imageKey)
            )
        }


    }
}