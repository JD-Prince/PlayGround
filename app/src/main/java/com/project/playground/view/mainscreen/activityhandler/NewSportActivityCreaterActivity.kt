package com.project.playground.view.mainscreen.activityhandler

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.playground.R
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.ActivityNewSportCreaterBinding
import com.project.playground.enums.Location
import com.project.playground.enums.MonthOfTheYear
import com.project.playground.enums.Sports
import java.io.ByteArrayOutputStream
import java.util.Calendar
import java.util.Locale
import kotlin.NumberFormatException
import kotlin.math.pow

class NewSportActivityCreaterActivity : AppCompatActivity() {

    private var _binding: ActivityNewSportCreaterBinding?=null

    private val binding
        get() = _binding!!

    private lateinit var viewModel : NewSportsActivityCreaterViewModel

    private var isImagePickerOpen=false
    private var compressedImageData:ByteArray?=null


    private var date : String?=null
    private var time : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = ActivityNewSportCreaterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel=ViewModelProvider(this,ViewModelFactory)[NewSportsActivityCreaterViewModel::class.java]

        setSupportActionBar(binding.toolbar.activityToolbar)

        supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(true)
            title= "New Activity"

        }

        viewModel.isRegistrationSuccessful.observe(this, Observer {
//            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("ACTION_NEW_ACTIVITY_ADDED"))
            finish()
        })

        binding.thumbnail.setOnClickListener {
            if (!isImagePickerOpen) {
                isImagePickerOpen = true

                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(galleryIntent)
            }
        }

        val locationItems : ArrayAdapter<String> = ArrayAdapter(baseContext,R.layout.list_item,Location.values().map { it.name }.toTypedArray())
        val sportsItems : ArrayAdapter<String> = ArrayAdapter(baseContext,R.layout.list_item,Sports.values().map { it.name }.toTypedArray())
        var title : String
        var location : Location?=null
        var sports : Sports?=null
        var description :String
        var otherInformation : String
        var requiredPlayer :Int


        binding.locationField.apply {
            setOnItemClickListener { _, _, position, _ ->
                location=Location.values()[position]
            }
            keyListener=null
        }
        binding.sportsfield.apply{
            setOnItemClickListener { _, _, position, _ ->
                sports=Sports.values()[position]
            }
            keyListener=null
        }
        binding.locationField.setAdapter(locationItems)
        binding.sportsfield.setAdapter(sportsItems)
        binding.submitButton.setOnClickListener {

            binding.apply {
                var allowance = true
                title=activityTitle.editText?.text.toString().trim()
                description=activityDescription.editText?.text.toString().trim()
                otherInformation=activityOtherInstruciton.editText?.text.toString().trim()
                requiredPlayer=0
                try {
                    requiredPlayer = noOfPlayersRequired.editText?.text.toString().let {
                        if (it.isEmpty()) {
                            noOfPlayersRequired.error = "Field Cannot be Empty"
                            allowance = false
                            -1
                        } else it.toInt()

                    }
                    if(requiredPlayer==0){
                        noOfPlayersRequired.error="Invalid Option"
                        allowance=false
                    }
                    if(requiredPlayer>1000) throw NumberFormatException()
                }catch (e : NumberFormatException){
                    noOfPlayersRequired.error="The value cannot exceed 1000"
                    allowance=false
                }

                if(date==null){
                    datePicker.error="Field Cannot be Empty"
                    allowance=false
                }
                if(time == null){
                    timePicker.error="Field Cannot be Empty"
                }

                if(sports==null){
                    sportsfieldoutline.error="choose a sport"
                    allowance=false
                }
                if(location==null){
                    locationfieldoutline.error="choose a location"
                    allowance=false
                }
                if(title.isBlank()){
                    activityTitle.error="Field Cannot be Empty"
                    allowance=false
                }
                if(!allowance) return@setOnClickListener
            }
            viewModel.addActivityToMyDatabase(

                title = title,
                sports = sports!!,
                description = description,
                location = location!!,
                otherInstructions = otherInformation,
                requiredPlayers = requiredPlayer,
                date=date!!,
                time=time!!,
                imageData=compressedImageData
            )

        }
        binding.apply {

            datePicker.setOnClickListener {
                datePicker.error=null
                val currentDate = Calendar.getInstance()
                val day = currentDate.get(Calendar.DAY_OF_MONTH)
                val month = currentDate.get(Calendar.MONTH)
                val year = currentDate.get(Calendar.YEAR)

                val datePickerDialog = DatePickerDialog(this@NewSportActivityCreaterActivity,R.style.CustomDateTimePickerDialogTheme,dateSetListener,year,month,day)
                datePickerDialog.datePicker.minDate=currentDate.let {
                    it.set(year,month,day+1)
                    it.timeInMillis
                }
                datePickerDialog.show()
            }
            timePicker.setOnClickListener {
                timePicker.error=null
                val currentTime = Calendar.getInstance()
                val timePickerDialogue = TimePickerDialog(this@NewSportActivityCreaterActivity,R.style.CustomDateTimePickerDialogTheme,timeSetListener,currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.SECOND),false)
                timePickerDialogue.show()
            }
            activityTitle.editText?.setOnFocusChangeListener { _, hasFocus ->
                if(hasFocus){
                    activityTitle.error=null
                }
            }
            sportsfield.setOnFocusChangeListener{_,hasFocus->
                if(hasFocus){
                    sportsfieldoutline.error=null
                }
            }
            locationField.setOnFocusChangeListener { _, hasFocus ->
                if(hasFocus) locationfieldoutline.error=null
            }
            noOfPlayersRequired.editText?.setOnFocusChangeListener{_,hasFocus -> if(hasFocus) noOfPlayersRequired.error=null}

        }



    }
    private val dateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
        val selectedDate = Calendar.getInstance()
        selectedDate.set(Calendar.YEAR,year)
        selectedDate.set(Calendar.MONTH,month)
        selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)

        val dateText = "${MonthOfTheYear.values()[month]} $dayOfMonth, $year"
        val formattedDate = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(selectedDate.time)
        date=formattedDate
        binding.datePicker.text=dateText

    }
    private val timeSetListener=TimePickerDialog.OnTimeSetListener{_,hourOfDay,minute->
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.time)
        time=formattedTime
        binding.timePicker.text=formattedTime
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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