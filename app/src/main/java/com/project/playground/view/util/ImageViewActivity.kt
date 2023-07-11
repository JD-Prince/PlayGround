package com.project.playground.view.util

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.playground.databinding.ActivityImageViewBinding
import com.squareup.picasso.Picasso

class ImageViewActivity : AppCompatActivity() {
    private var _binding : ActivityImageViewBinding?=null
    private val binding : ActivityImageViewBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = intent.getStringExtra("IMAGE_ARRAY")
        println(imageUri)

        if(imageUri != null) {
            val picasso = Picasso.get()
            picasso.load(Uri.parse(imageUri)).into(binding.image)
        }
        else{
            binding.emptyFieldCaption.visibility= View.VISIBLE
        }

    }
}