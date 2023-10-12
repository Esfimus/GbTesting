package com.esfimus.gbtesting.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityTitle() {
        val title = uiDevice.findObject(By.res(packageName, "title"))
        Assert.assertNotNull(title)
    }

    @Test
    fun test_MainActivityInputBox() {
        val inputBox = uiDevice.findObject(By.res(packageName, "inputBox"))
        Assert.assertNotNull(inputBox)
    }

    @Test
    fun test_MainActivityInputCopyText() {
        val inputCopy = uiDevice.findObject(By.res(packageName, "inputCopy"))
        Assert.assertNotNull(inputCopy)
    }

    @Test
    fun test_MainActivitySaveButton() {
        val saveButton = uiDevice.findObject(By.res(packageName, "saveButton"))
        Assert.assertNotNull(saveButton)
    }

    @Test
    fun test_EmptyFieldClickSaveButton() {
        val saveButton = uiDevice.findObject(By.res(packageName, "saveButton"))
        val inputCopy = uiDevice.findObject(By.res(packageName, "inputCopy"))
        saveButton.click()
        Assert.assertEquals(inputCopy.text, "Empty")
    }

    @Test
    fun test_EditFieldClickSaveButton() {
        val saveButton = uiDevice.findObject(By.res(packageName, "saveButton"))
        val inputCopy = uiDevice.findObject(By.res(packageName, "inputCopy"))
        val inputText = uiDevice.findObject(By.res(packageName, "inputText"))
        inputText.text = "Test text"
        saveButton.click()
        Assert.assertEquals(inputCopy.text, "Test text")
    }
}