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
    private lateinit var removeButton: Button
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.titleTextView)
        removeButton = findViewById(R.id.removeButton)
        removeButton.setOnClickListener {
            state = State.Removed
            state.apply(rootLayout, textView, removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, State::class.java) as State
        } else {
            savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(rootLayout, textView, removeButton)
    }

    interface State : Serializable {
        fun apply(linearLayout: LinearLayout, textView: TextView, button: Button)

        object Initial : State {
            override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) =
                Unit
        }

        object Removed : State {
            override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) {
                linearLayout.removeView(textView)
                button.isEnabled = false
            }
        }
    }

    companion object {
        private const val KEY = "key"
    }


}