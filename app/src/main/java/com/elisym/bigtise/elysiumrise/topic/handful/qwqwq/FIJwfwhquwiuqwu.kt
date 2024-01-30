package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import android.app.Application
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FIJwfwhquwiuqwu: Application() {
    companion object {
        val li = "https://elysiumrise"
        const val HUFHwhwhfuwhu = "05a40a02-fb30-4edf-8f52-aca3158773aa"
    }

    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this, HUFHwhwhfuwhu)
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}