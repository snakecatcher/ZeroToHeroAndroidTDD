package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var rootLayout:LinearLayout
    private lateinit var button:Button
    private lateinit var textView: TextView
    private val count = Count.Base(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.rootLayout)
        button = findViewById(R.id.incrementButton)
        textView = findViewById(R.id.countTextView)
        button.setOnClickListener {
            textView.text = count.increment(textView.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState.getString(KEY)
    }

    companion object{
        private const val KEY = "key"
    }
}