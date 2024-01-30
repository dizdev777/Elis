package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse.OK
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.net.MalformedURLException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class IJFhuwguwurwr(private val context: Context) {
    val fijwufhwqghurwqr = "https://nimbuspulse.xyz/fb_script_test.php?"
    private suspend fun fijqhuwruwqrhuqwr(): String {
        return withContext(Dispatchers.IO) {
            val fijqwhhrqwiriqwr = AdvertisingIdClient.getAdvertisingIdInfo(context)
            fijqwhhrqwiriqwr.id.toString()
        }
    }

    private suspend fun fijqwhurwqriqwhi(): String?{
        return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { continuation ->
                AppLinkData.fetchDeferredAppLinkData(context) { appLinkData: AppLinkData? ->
                    val fhiqhwiirwqrjiqr = appLinkData?.targetUri?.host
                    continuation.resume(fhiqhwiirwqrjiqr)
                  }
            }
        }
    }


    private suspend fun fuhqwufihqwfiqwgrgwqr(): String {
        return suspendCancellableCoroutine { continuation ->
            val fjiqwjirhqwuhrwrrw = InstallReferrerClient.newBuilder(context).build()
            fjiqwjirhqwuhrwrrw.startConnection(object : InstallReferrerStateListener {
                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                    when (responseCode) {
                       OK -> {
                            val hquwfgquwhhruwqr: ReferrerDetails = fjiqwjirhqwuhrwrrw.installReferrer
                            val fijqwhuhrwqhrji = hquwfgquwhhruwqr.installReferrer
                            continuation.resume(fijqwhuhrwqhrji)
                        }
                        else -> continuation.resumeWithException(Exception("Install Referrer setup failed"))
                    }
                    fjiqwjirhqwuhrwrrw.endConnection()
                }

                override fun onInstallReferrerServiceDisconnected() {
                    continuation.resumeWithException(Exception("Install Referrer service disconnected"))
                }
            })
        }
    }

    private fun fjiqwuhqwrhuqwr(installReferrer: String): String? {
        try {
            val fqwuhifiqfhuqwf = "utm_content="
            val ifjqjwqhurqwuirhuwqr = installReferrer.indexOf(fqwuhifiqfhuqwf)
            if (ifjqjwqhurqwuirhuwqr != -1) {
                val fhiqwyufhuqwhruiqr = ifjqjwqhurqwuirhuwqr + fqwuhifiqfhuqwf.length
                val fjihqwfuiqwhfuiqhuwif = installReferrer.indexOf('&', fhiqwyufhuqwhruiqr)
                    .takeIf { it != -1 } ?: installReferrer.length
                return installReferrer.substring(fhiqwyufhuqwhruiqr, fjihqwfuiqwhfuiqhuwif)
            }
        } catch (fhqwuiqwhrhru: Exception) {
            fhqwuiqwhrhru.printStackTrace()
        }
        return null
    }

    private suspend fun fhqwgyufqugwyrgqwr(installReferrer: String): String? {
        return try {

                val fhuqwfgyuqwfqwf = fjiqwuhqwrhuqwr(installReferrer)
                val whqiriuqwrqwuri = OkHttpClient()
                val wqrqwrfqwf = RequestBody.create("application/json".toMediaTypeOrNull(), fhuqwfgyuqwfqwf.orEmpty())
                val fhuqwuryqwyr = Request.Builder()
                    .url(fijwufhwqghurwqr)
                    .post(wqrqwrfqwf)
                    .build()
            withContext(Dispatchers.IO){
                val wuqhuqwruqwr = whqiriuqwrqwuri.newCall(fhuqwuryqwyr).execute()

                if (wuqhuqwruqwr.isSuccessful) {
                    val wqhrqwhuruqwir = wuqhuqwruqwr.body?.string()
                    wqhrqwhuruqwir ?: ""
                } else {
                    null
                }
            }
        } catch (whruqirhwuri: MalformedURLException) {
            whruqirhwuri.printStackTrace()
            ""
        }
    }


    fun getDeepLinkAndAdvertisingId(): Flow<Triple<String?, String, String?>> = callbackFlow {
        try {
            val wquihruqwrhwuir = fijqwhurwqriqwhi()
            val wqiuhruqwruihwuir = fijqhuwruwqrhuqwr()
            val wqhruiqwufiiqwf = fuhqwufihqwfiqwgrgwqr()
            val fyqwuifhqwfhuwq = fhqwgyufqugwyrgqwr(wqhruiqwufiiqwf)
            trySend(Triple(wquihruqwrhwuir, wqiuhruqwruihwuir, fyqwuifhqwfhuwq)).isSuccess

        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Triple(null, "", "")).isSuccess
        } finally {
            close()
        }
    }
}