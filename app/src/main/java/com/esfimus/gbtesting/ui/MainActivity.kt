package com.esfimus.gbtesting.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esfimus.gbtesting.R
import com.esfimus.gbtesting.databinding.ActivityMainBinding
import com.esfimus.gbtesting.domain.EmailValidator

class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding
    private val emailValidator = EmailValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initView()
    }

    private fun initView() {
        with (ui) {
            saveButton.setOnClickListener {
                val enteredText = inputText.text
                if (enteredText.isNullOrBlank()) {
                    inputCopy.text = getString(R.string.empty)
                } else {
                    inputCopy.text = inputText.text
                }
                if (emailValidator.isValid(enteredText)) {
                    "${getString(R.string.eMailIsValid)} ${emailValidator.getEmailCharCount(enteredText)}"
                        .message()
                } else {
                    "${getString(R.string.error)} ${emailValidator.getEmailCharCount(enteredText)}"
                        .message()
                }
            }
        }
    }

    private fun String.message() {
        Toast.makeText(this@MainActivity, this, Toast.LENGTH_SHORT).show()
    }
}