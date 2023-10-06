package com.esfimus.gbtesting

import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esfimus.gbtesting.ui.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_AssertIsNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextInputLayout_IsNotNull() {
        scenario.onActivity {
            val inputBox = it.findViewById<TextInputLayout>(R.id.inputBox)
            assertNotNull(inputBox)
        }
    }

    @Test
    fun activityTextInputEditText_IsNotNull() {
        scenario.onActivity {
            val textInputField = it.findViewById<TextInputEditText>(R.id.inputText)
            assertNotNull(textInputField)
        }
    }

    @Test
    fun activityTextView_IsVisible() {
        scenario.onActivity {
            val titleTextView = it.findViewById<TextView>(R.id.title)
            assertEquals(View.VISIBLE, titleTextView.visibility)
        }
    }

    @Test
    fun activityTextView_HasText() {
        scenario.onActivity {
            val titleTextView = it.findViewById<TextView>(R.id.title)
            assertEquals("TEST APP", titleTextView.text)
        }
    }

    @Test
    fun activityEditText_IsNotNull() {
        scenario.onActivity {
            val textInputField = it.findViewById<TextInputEditText>(R.id.inputText)
            textInputField.setText("edit text")
            assertNotNull(textInputField.text)
            assertEquals("edit text", textInputField.text.toString())
        }
    }

    @Test
    fun activityButton_IsVisible() {
        scenario.onActivity {
            val saveButton = it.findViewById<Button>(R.id.saveButton)
            assertEquals(View.VISIBLE, saveButton.visibility)
        }
    }

    @Test
    fun activityButton_IsFunctional() {
        scenario.onActivity {
            val saveButton = it.findViewById<Button>(R.id.saveButton)
            val textInputField = it.findViewById<TextInputEditText>(R.id.inputText)
            val inputCopyTextView = it.findViewById<TextView>(R.id.inputCopy)

            textInputField.setText("e-mail")
            saveButton.performClick()
            assertEquals("e-mail", inputCopyTextView.text.toString())
        }
    }

    @Test
    fun activityButton_ActivatesResult() {
        scenario.onActivity {
            val saveButton = it.findViewById<Button>(R.id.saveButton)
            val textInputField = it.findViewById<TextInputEditText>(R.id.inputText)

            textInputField.setText("funk")
            saveButton.performClick()
            assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(("Error 4"))
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}