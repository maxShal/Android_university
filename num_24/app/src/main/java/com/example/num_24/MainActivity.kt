package com.example.num_24

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST: Int = 1
    private val PIC_CROP: Int = 2
    private var picUri: Uri? = null
    private var currentPhotoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Съёмка и Кадрирование"
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE)
        }
    }

    fun onClick(v: View?) {
        try {
            // Намерение для запуска камеры
            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            // Создаем файл для сохранения фото
            val photoFile = createImageFile()
            if (photoFile != null) {
                picUri = FileProvider.getUriForFile(this, "${applicationContext.packageName}.fileprovider", photoFile)
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri)
                startActivityForResult(captureIntent, CAMERA_REQUEST)
            }
        } catch (e: ActivityNotFoundException) {
            // Выводим сообщение об ошибке
            val errorMessage = "Ваше устройство не поддерживает съемку"
            val toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                // Сохраняем фото в галерею
                galleryAddPic()
                // Кадрируем фото
                performCrop()
            } else if (requestCode == PIC_CROP) {
                val extras = data?.extras
                val thePic = extras?.getParcelable<Bitmap>("data")
                val picView = findViewById<ImageView>(R.id.picture)
                picView.setImageBitmap(thePic)
            }
        }
    }

    private fun performCrop() {
        try {
            val cropIntent = Intent("com.android.camera.action.CROP")
            cropIntent.setDataAndType(picUri, "image/*")
            cropIntent.putExtra("crop", "true")
            cropIntent.putExtra("aspectX", 1)
            cropIntent.putExtra("aspectY", 1)
            cropIntent.putExtra("outputX", 256)
            cropIntent.putExtra("outputY", 256)
            cropIntent.putExtra("return-data", true)
            startActivityForResult(cropIntent, PIC_CROP)
        } catch (anfe: ActivityNotFoundException) {
            val errorMessage = "Извините, но ваше устройство не поддерживает кадрирование"
            val toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Создаем уникальное имя для изображения
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath!!)
            picUri = Uri.fromFile(f)
            mediaScanIntent.data = picUri
            sendBroadcast(mediaScanIntent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val PERMISSION_REQUEST_CODE = null
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Разрешения получены, продолжаем работу
            } else {
                Toast.makeText(this, "Без разрешений камера и галерея не работают", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
