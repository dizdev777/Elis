package com.elisym.bigtise.elysiumrise.topic.handful.rise
import android.content.Context

class DataUtils(fiqwhriw: Context) {

    private val fhiquwhfqwifi = "${fiqwhriw.packageName}_prefs"
    private val fqwhifiqwjrjiw = "lastOpenedUrl"
    var l = ""
    val fqwuifjqwhfqiwirj = fiqwhriw.getSharedPreferences(fhiquwhfqwifi, Context.MODE_PRIVATE)

    fun saveLastOpenedUrl(url: String) {
        if(l == ""){
            l = getLastOpenedUrl(url).toString()
            fqwuifjqwhfqiwirj.edit().putString(fqwhifiqwjrjiw, url).apply()
        }
    }

    fun getLastOpenedUrl(url: String): String? {
        return fqwuifjqwhfqiwirj.getString(fqwhifiqwjrjiw, url)
    }
}
