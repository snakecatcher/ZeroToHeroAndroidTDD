package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnChange:Button = findViewById(R.id.changeButton)
        val textHello:TextView = findViewById(R.id.titleTextView)
        btnChange.setOnClickListener {
            textHello.text = "I am an Android Developer!"
        }

    }
}