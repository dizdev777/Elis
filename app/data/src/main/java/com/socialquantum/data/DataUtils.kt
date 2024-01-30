package com.socialquantum.data
import android.content.Context

class DataUtils(context: Context) {

    private val PREFS_NAME = "${context.packageName}_prefs"
    private val KEY_LAST_OPENED_URL = "lastOpenedUrl"
    var l = ""
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLastOpenedUrl(url: String) {
        if(l == ""){
            l = getLastOpenedUrl(url).toString()
            sharedPreferences.edit().putString(KEY_LAST_OPENED_URL, url).apply()
        }
    }

    fun getLastOpenedUrl(url: String): String? {
        return sharedPreferences.getString(KEY_LAST_OPENED_URL, url)
    }
}
