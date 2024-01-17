package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rootLayout: LinearLayout
    private lateinit var titleTextView: TextView
    private lateinit var actionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        rootLayout = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        actionButton = findViewById(R.id.actionButton)

        actionButton.setOnClickListener {
            actionButton.isEnabled = false
            progressBar.visibility = View.VISIBLE

            Timer().schedule(object : TimerTask() {
                override fun run() = runOnUiThread {
                    titleTextView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    actionButton.isEnabled = true
                }
            }, 3000)

            /*
            Handler(Looper.getMainLooper()).postDelayed({
                titleTextView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                actionButton.isEnabled = true
            }, 3000)

            actionButton.postDelayed({
                titleTextView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                actionButton.isEnabled = true
            }, 3000)
             */
        }
    }
}