package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.titleTextView)

        button.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this){uiState ->
            uiState.apply(button, progressBar, textView)
        }



    }
}