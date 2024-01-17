package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState {
    fun apply(decrementButton: Button, incrementButton: Button, countTextView: TextView)
    data class Base(private val text: String) : UiState {
        override fun apply(
            decrementButton: Button,
            incrementButton: Button,
            countTextView: TextView
        ) {
            countTextView.text = text
            decrementButton.isEnabled = true
            incrementButton.isEnabled = true
        }

    }
    data class Min(private val text: String) : UiState {
        override fun apply(
            decrementButton: Button,
            incrementButton: Button,
            countTextView: TextView
        ) {
            countTextView.text = text
            decrementButton.isEnabled = false
            incrementButton.isEnabled = true
        }

    }
    data class Max(private val text: String) : UiState {
        override fun apply(
            decrementButton: Button,
            incrementButton: Button,
            countTextView: TextView
        ) {
            countTextView.text = text
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
        }

    }

}
