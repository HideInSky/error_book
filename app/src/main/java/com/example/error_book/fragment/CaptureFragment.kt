package com.example.error_book.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.example.error_book.R
import com.example.error_book.databinding.FragmentCaptureBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CaptureFragment : Fragment() {

    lateinit var currentPhotoPath: String
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var imageView: ImageView

//    private fun dispatchTakePictureIntent() {
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        try {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//        } catch (e: ActivityNotFoundException) {
//            // display error state to the user
//
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCaptureBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_capture, container, false)

        imageView = binding.imgCaptured
        binding.btnCaptureImage.setOnClickListener { view ->
            dispatchTakePictureIntent()
        }

        return binding.root
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
            imageView.setImageDrawable(null)
            imageView.setImageBitmap(imageBitmap)
        } else
            Toast.makeText(activity, "onActivityResult: Cannot get photo",
                    Toast.LENGTH_SHORT).show()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Toast.makeText(activity, "DispatchTakePictureIntentError: Cannot invoke camera",
                Toast.LENGTH_SHORT).show()
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        activity!!,
                        "com.example.RecyclerViewInside.fileprovider",
                        it
                    )
                    Log.e("ERROR TEST", "dispatch Result2")
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    Log.e("ERROR TEST", "dispatch Result2")
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }


}