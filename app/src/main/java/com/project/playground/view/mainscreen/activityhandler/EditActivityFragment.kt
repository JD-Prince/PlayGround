package com.project.playground.view.mainscreen.activityhandler

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.playground.R
import com.project.playground.databinding.FragmentEditActivityBinding
import com.project.playground.model.SportActivity
import java.io.ByteArrayOutputStream
import kotlin.math.pow

class EditActivityFragment(private val gameHandler : GameHandler) : BottomSheetDialogFragment() {

    private var _binding : FragmentEditActivityBinding?=null
    private val binding : FragmentEditActivityBinding
        get()=_binding!!

    private var isImagePickerOpen=false
    private var compressedImageData:ByteArray?=null


    private val currentEvent = gameHandler.getGame()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentEditActivityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            currentEvent.thumbnail?.let { image ->
                thumbnail.setImageBitmap(
                    BitmapFactory.decodeByteArray(
                        image,
                        0,
                        image.size
                    )
                )
                compressedImageData=image
            }
            activityDescription.editText?.setText(currentEvent.description)
            otherInformation.editText?.setText(currentEvent.otherInstructions)
            requiredPlayers.editText?.setText(currentEvent.requiredPlayers.toString())

            thumbnail.setOnClickListener {
                if (!isImagePickerOpen) {
                    isImagePickerOpen = true

                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryLauncher.launch(galleryIntent)
                }
            }
//
//            requiredPlayers.editText?.setOnClickListener{
//                requiredPlayers.error=null
//            }

            updateBtn.setOnClickListener {
                val description  = activityDescription.editText?.text.toString().trim()
                val otherInformation = otherInformation.editText?.text.toString().trim()
                var requiredplayers = 0
                try{
                    if(requiredPlayers.editText?.text.toString().isNullOrBlank()) {
                        requiredPlayers.editText?.error="Field cannot be empty"
                        return@setOnClickListener
                    }
                    requiredplayers= requiredPlayers.editText?.text.toString().toInt()

                    if(requiredplayers > 1000) throw NumberFormatException()
                    if(requiredplayers < currentEvent.requiredPlayers){
                        requiredPlayers.editText?.error = "The value cannot be lesser than inital value (initial value ${currentEvent.requiredPlayers})"
                        return@setOnClickListener
                    }
                }catch (e : NumberFormatException){
                    requiredPlayers.editText?.error = "value cannot exceed 1000"
                    return@setOnClickListener
                }
                currentEvent.apply {
                    this.description = description
                    this.otherInstructions = otherInformation
                    this.requiredPlayers = requiredplayers
                    this.thumbnail=compressedImageData
                }
                gameHandler.updateGame(currentEvent)
                dismiss()

            }




        }


    }
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        isImagePickerOpen = false
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri: Uri? = data?.data
            binding.thumbnail.setImageURI(selectedImageUri)
            selectedImageUri?.let { uri ->
                compressedImageData = compressImage(uri)
            }
        }
    }
    private fun compressImage(imageUri: Uri): ByteArray? {
        val maxSizeInBytes = 2000*1024
        val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
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
        val compressedBitmap = BitmapFactory.decodeStream(requireActivity().contentResolver.openInputStream(imageUri), null, decodeOptions)
        compressedBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        compressedBitmap?.recycle()
        return outputStream.toByteArray()
    }

    interface GameHandler{
        fun getGame() : SportActivity
        fun updateGame(game : SportActivity)
    }

}