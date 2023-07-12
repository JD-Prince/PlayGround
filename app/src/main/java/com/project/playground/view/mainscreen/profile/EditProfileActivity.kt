package com.project.playground.view.mainscreen.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.project.playground.R
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivityEditProfileBinding
import com.project.playground.enums.EditProfileStatements
import java.io.ByteArrayOutputStream
import kotlin.math.pow

class EditProfileActivity : AppCompatActivity() {
    private var _binding : ActivityEditProfileBinding? = null
    private val binding : ActivityEditProfileBinding
        get()=_binding!!
    private val viewModel:EditProfileViewModel by viewModels{
        ViewModelFactory
    }
    private var isImagePickerOpen=false
    private var compressedImageData:ByteArray?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        _binding= ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.activityToolbar.activityToolbar.setBackgroundColor(ContextCompat.getColor(baseContext,R.color.brightblue))

        setSupportActionBar(binding.activityToolbar.activityToolbar)
        supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(true)
            title="Edit Profile"

        }

        viewModel.loadUserData().observe(this){player->
            viewModel.currentUser=player
            loadAllData()
        }
        viewModel.result.observe(this){result->
            when(result){
                EditProfileStatements.UPDATED_SUCCESSFUL->{
                    finish()
                }
                EditProfileStatements.USERNAME_IS_NOT_AVAILABLE->
                {
                    binding.usernamaeLayout.error="Username Not Available"
                }
            }

        }


        binding.profilePic.setOnClickListener{
                if (!isImagePickerOpen) {
                    isImagePickerOpen = true

                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryLauncher.launch(galleryIntent)
                }


        }
        binding.alias.editText?.setOnFocusChangeListener{_,hasFocus->
            if(hasFocus)binding.alias.error=null
        }
        binding.usernamaeLayout.editText?.setOnFocusChangeListener{_,hasView->
            if(hasView) binding.usernamaeLayout.error=null
        }
        binding.phonenumberLayout.editText?.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) binding.phonenumberLayout.error=null
        }
        binding.saveButton.setOnClickListener {
            var allowance =true

            binding.apply {
                val name = alias.editText?.text.toString().trim()
                val bio = bio.editText?.text.toString().trim()
                val username = usernamaeLayout.editText?.text.toString().trim()
                val phonenumer = phonenumberLayout.editText?.text.toString()!!.trim()
                if(name.isNullOrBlank()){
                    alias.error="Field cannot be empty"
                    allowance=false
                }
                if(username.isNullOrBlank()){
                    usernamaeLayout.error="Field cannot be empty"
                    allowance = false
                }
                val phoneNumberRegex = """^(\+?91|0)?[6789]\d{9}$""".toRegex()
                if(! phonenumer.matches(phoneNumberRegex)){
                    phonenumberLayout.error = "Please Enter the Valid Number"
                    allowance=false
                }

                viewModel.name=name
                viewModel.bio=bio
                viewModel.imageArray=compressedImageData
                viewModel.username=username
                viewModel.phoneNumber=phonenumer


            }
            if(allowance) {
            viewModel.updateData()
                val sharedPreferences = getSharedPreferences("LOGIN_PREF",Context.MODE_PRIVATE).edit().putString("USERNAME",viewModel.username)
        }


        }



    }

    private fun loadAllData() {
        println("Data loadded here ${viewModel.username}")
        binding.alias.editText?.setText(viewModel.name)
        binding.bio.editText?.setText(viewModel.bio)
        binding.usernamaeLayout.editText?.setText(viewModel.username)
        binding.phonenumberLayout.editText?.setText(viewModel.phoneNumber)
        viewModel.imageArray?.let {image->
            binding.profilePic.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.size))
            compressedImageData=image
        }

    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        isImagePickerOpen = false
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri: Uri? = data?.data
            binding.profilePic.setImageURI(selectedImageUri)
            selectedImageUri?.let { uri ->
                compressedImageData = compressImage(uri)
            }
        }
    }
    private fun compressImage(imageUri: Uri): ByteArray? {
        val maxSizeInBytes = 2000*1024
        val inputStream = this.contentResolver.openInputStream(imageUri)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(inputStream, null, options)
        inputStream?.close()

        var scale = 1
        while ((options.outWidth * options.outHeight * (1 / scale.toDouble().pow(2.0))) > maxSizeInBytes) {
            scale++
        }

        val outputStream = ByteArrayOutputStream()
        val decodeOptions = BitmapFactory.Options()
        decodeOptions.inSampleSize = scale
        val compressedBitmap = BitmapFactory.decodeStream(this.contentResolver.openInputStream(imageUri), null, decodeOptions)
        compressedBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        compressedBitmap?.recycle()
        return outputStream.toByteArray()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                //todo save as draft
                finish()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}