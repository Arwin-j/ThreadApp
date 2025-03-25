package com.example.threadapp1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val countdownTextview : TextView by lazy {
       findViewById(R.id.countdownTextview)
    val countdownHandler = Handler(Looper.getMainLooper()){
       countdownTextview.text = it.what.toString()
       true
    } 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        Thread{
            repeat(100){
                val countdown = (100-it)
                Log.d("Countdown: ", countdown.toString())
            
                countdownHandler.sendEmptyMessage(countdown)

                Thread.sleep(1000)
            }
        }.start()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
