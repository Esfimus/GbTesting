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
                if (emailValidator.isValid(inputText.text)) {
                    getString(R.string.eMailIsValid).message()
                } else {
                    getString(R.string.error).message()
                }
            }
        }
    }

    private fun String.message() {
        Toast.makeText(this@MainActivity, this, Toast.LENGTH_SHORT).show()
    }
}