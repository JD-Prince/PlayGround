package com.project.playground.view.mainscreen.profile

import android.content.Context
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
import androidx.fragment.app.viewModels
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentProfileBinding
import com.project.playground.enums.ConfrimationStatus
import com.project.playground.view.auth.AuthActivity
import com.project.playground.view.dialogues.ConfrimationDialogueFragment
import com.project.playground.view.util.ImageViewActivity
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {

    private var _binding:FragmentProfileBinding?=null
    private val binding : FragmentProfileBinding
        get()=_binding!!

    private val viewModel : ProfileViewModel by viewModels {
        ViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserData().observe(viewLifecycleOwner){
            playerObj->
            binding.profilePic.setOnClickListener {
                val intent = Intent(requireContext(), ImageViewActivity::class.java)
                playerObj.profilePicture?.let {
                    val imageUri = getImageUriFromImageArray(it)
                    intent.putExtra("IMAGE_ARRAY",imageUri.toString())
                }
                startActivity(intent)
            }
            playerObj.profilePicture?.let { image->
                binding.profilePic.setImageBitmap(
                    BitmapFactory.decodeByteArray(
                        image,0,image.size
                    )
                )
            }
            binding.bio.text=playerObj.bio
            binding.alias.text=playerObj.alias
            binding.eventPts.text=playerObj.totalEvents.toString()
            binding.pointsPts.text=playerObj.points.toString()
            binding.contact.text=playerObj.phoneNumber
            binding.username.text=playerObj.userName
            binding.organizingGame.text=playerObj.organizingEventCount.toString()
            binding.enrolledGames.text=playerObj.enrolledEventCount.toString()

        }
//


    }
    private fun getImageUriFromImageArray(imageArray: ByteArray): Uri {
        val bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.size)
        val uri = getImageUriFromBitmap(bitmap)
        return uri
    }

    private fun getImageUriFromBitmap(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val uri = Uri.parse(
            MediaStore.Images.Media.insertImage(
                requireContext().contentResolver,
                bitmap,
                "Image",
                "Image"
            ))
        return uri
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding=null
    }

}