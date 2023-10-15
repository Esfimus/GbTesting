package com.esfimus.gbtesting.automator

import android.content.Context
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.esfimus.gbtesting.TIMEOUT
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class OpenOtherApps {
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Test
    fun test_OpenMaps() {
        uiDevice.pressHome()
        uiDevice.wait(Until.gone(By.res(packageName)), TIMEOUT)
        uiDevice.swipe(300, 1500, 300, 500, 5)
        val appView = UiScrollable(UiSelector().scrollable(true))
        val mapsApp = appView.getChildByText(UiSelector().className(TextView::class.java.name), "Maps")
        mapsApp.clickAndWaitForNewWindow()
        val mapsAppId = uiDevice.findObject(UiSelector().packageName("com.google.android.apps.maps"))
        Assert.assertTrue(mapsAppId.exists())
    }

    @Test
    fun test_OpenYoutube() {
        uiDevice.pressHome()
        uiDevice.wait(Until.gone(By.res(packageName)), TIMEOUT)
        uiDevice.swipe(200, 1000, 200, 0, 5)
        val appView = UiScrollable(UiSelector().scrollable(true))
        val youtubeApp = appView.getChildByText(UiSelector().className(TextView::class.java.name), "YouTube")
        youtubeApp.clickAndWaitForNewWindow()
        val youtubeAppId = uiDevice.findObject(UiSelector().packageName("com.google.android.youtube"))
        Assert.assertTrue(youtubeAppId.exists())
    }
}