package com.example.grocerease.utils;


import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

class ImageUtils(val activity:AppCompatActivity) {
        lateinit var ActivityResultLauncher: ActivityResultLauncher<Intent>

        fun registerActivity(callback: (Uri?) -> Unit){
            activityResultLauncher = activity.registerForActivityResult(
                    ActivityResultContracts.StartActivityForResult(),
                    ActivityResultCallback { result ->
                    val resultcode = result.resultCode
                val imageData = result.data
                if (resultcode == AppCompatActivity.RESULT_OK && imageData != null) {
                    var imageUri = imageData.data
                    callback(imageUri)
                }

            })
        }
        fun launchGallery(context: Context){
            var permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                android.Manifest.permission.READ_MEDIA_IMAGES
            } else {
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            }
            if (ContextCompat.checkSelfPermission(
                    context,
                    permissions
            ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(activity, arrayOf(permissions), 1)
            } else {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                activityResultLauncher.launch(intent)
            }
        }
    }
