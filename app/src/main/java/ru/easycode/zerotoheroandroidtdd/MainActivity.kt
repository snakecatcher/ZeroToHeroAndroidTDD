package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var rootLayout: LinearLayout
    private var state:State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.titleTextView)
        val btnRemove = findViewById<Button>(R.id.removeButton)
        btnRemove.setOnClickListener {
            state = State.Removed
            state.apply(rootLayout, textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.TIRAMISU){
            state = savedInstanceState.getSerializable(KEY, State::class.java) as State
        } else {
            state = savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(rootLayout, textView)
    }

    interface State: Serializable{
        fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
        object Initial: State{
            override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
        }

        object Removed: State {
            override fun apply(linearLayout: LinearLayout, textView: TextView) {
                linearLayout.removeView(textView)
            }

        }
    }
    companion object {
        private const val KEY = "key"
    }
}