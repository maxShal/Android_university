package com.example.num_10

import android.annotation.SuppressLint
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private lateinit var assertManager: AssetManager

    private var catSound: Int = 0
    private var chickenSound: Int = 0
    private var cowSound: Int = 0
    private var horseSound: Int = 0

    private var streamID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val catImage: ImageView = findViewById(R.id.ICat)
        val chickenImage: ImageView = findViewById(R.id.IChicken)
        val horseImage: ImageView = findViewById(R.id.IHorse)
        val cowImage: ImageView = findViewById(R.id.ICow)

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()

        assertManager = assets
        catSound = loadSound("cat.mp3")
        chickenSound = loadSound("chicken.mp3")
        cowSound = loadSound("cow.mp3")
        horseSound = loadSound("horse.mp3")


        catImage.setOnClickListener { playSound(catSound) }
        chickenImage.setOnClickListener { playSound(chickenSound) }
        cowImage.setOnClickListener { playSound(cowSound) }
        horseImage.setOnClickListener { playSound(horseSound) }
    }

    override fun onPause(){
        super.onPause()

        soundPool.release()
    }

    private fun playSound(sound: Int): Int{
        if(sound > 0){
            streamID = soundPool.play(sound, 1F, 1F, 1, 0, 1F)
        }
        return streamID
    }

    private fun loadSound(fileName: String): Int {
        val afd: AssetFileDescriptor = try{
            application.assets.openFd(fileName)
        }catch(e: IOException){
            e.printStackTrace()
            Log.d(":(","Не могу загрузить файл")
            return  -1
        }
        return soundPool.load(afd, 1)
    }
}