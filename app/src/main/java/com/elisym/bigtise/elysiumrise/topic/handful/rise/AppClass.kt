package com.elisym.bigtise.elysiumrise.topic.handful.rise

import android.app.Application
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppClass: Application() {
    companion object {
        val li = "https://elysiumrise"
        const val ONESIGNAL_APP_ID = "########-####-####-####-############"
    }

    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}