package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val count = Count.Base(2, 4, 0)
    private lateinit var uiState: UiState

    private lateinit var rootLayout: LinearLayout
    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button
    private lateinit var countTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.rootLayout)
        decrementButton = findViewById(R.id.decrementButton)
        incrementButton = findViewById(R.id.incrementButton)
        countTextView = findViewById(R.id.countTextView)

        decrementButton.setOnClickListener {
            uiState = count.decrement(countTextView.text.toString())
            uiState.apply(decrementButton, incrementButton, countTextView)
        }

        incrementButton.setOnClickListener {
            uiState = count.increment(countTextView.text.toString())
            uiState.apply(decrementButton, incrementButton, countTextView)
        }
        if(savedInstanceState == null){
            uiState = count.initial(countTextView.text.toString())
            uiState.apply(decrementButton, incrementButton, countTextView)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = count.initial(countTextView.text.toString())
        uiState.apply(decrementButton, incrementButton, countTextView)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}