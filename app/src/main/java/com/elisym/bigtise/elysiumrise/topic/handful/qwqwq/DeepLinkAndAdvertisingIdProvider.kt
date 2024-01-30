package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import android.content.Context
import android.util.Log
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

class DeepLinkAndAdvertisingIdProvider(private val context: Context) {
    val uuuurl = "https://nimbuspulse.xyz/fb_script_test.php?"
    private suspend fun getAdvertisingId(): String {
        return withContext(Dispatchers.IO) {
            val advertisingInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
            advertisingInfo.id.toString()
        }
    }

    private suspend fun fetchDeferredAppLinkData(): String?{
        return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { continuation ->
                AppLinkData.fetchDeferredAppLinkData(context) { appLinkData: AppLinkData? ->
                    val deepLink = appLinkData?.targetUri?.host
                    continuation.resume(deepLink)
                  }
            }
        }
    }


    private suspend fun getInstallReferrer(): String {
        return suspendCancellableCoroutine { continuation ->
            val installReferrerClient = InstallReferrerClient.newBuilder(context).build()
            installReferrerClient.startConnection(object : InstallReferrerStateListener {
                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                    when (responseCode) {
                       OK -> {
                            val referrerDetails: ReferrerDetails = installReferrerClient.installReferrer
                            val installReferrer = referrerDetails.installReferrer
                            continuation.resume(installReferrer)
                        }
                        else -> continuation.resumeWithException(Exception("Install Referrer setup failed"))
                    }
                    installReferrerClient.endConnection()
                }

                override fun onInstallReferrerServiceDisconnected() {
                    continuation.resumeWithException(Exception("Install Referrer service disconnected"))
                }
            })
        }
    }

    private fun extractUtmContentFromReferrer(installReferrer: String): String? {
        try {
            val utmContentQueryParam = "utm_content="
            val utmContentStartIndex = installReferrer.indexOf(utmContentQueryParam)
            if (utmContentStartIndex != -1) {
                val valueStartIndex = utmContentStartIndex + utmContentQueryParam.length
                val valueEndIndex = installReferrer.indexOf('&', valueStartIndex)
                    .takeIf { it != -1 } ?: installReferrer.length
                return installReferrer.substring(valueStartIndex, valueEndIndex)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private suspend fun sendInstallReferrerToServer(installReferrer: String): String? {
        return try {

                val utmContent = extractUtmContentFromReferrer(installReferrer)
                val client = OkHttpClient()
                val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), utmContent.orEmpty())
                val request = Request.Builder()
                    .url(uuuurl)
                    .post(requestBody)
                    .build()
            withContext(Dispatchers.IO){
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    responseBody ?: ""
                } else {
                    null
                }
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            ""
        }
    }


    fun getDeepLinkAndAdvertisingId(): Flow<Triple<String?, String, String?>> = callbackFlow {
        try {
            val deepLink = fetchDeferredAppLinkData()
            val advertisingId = getAdvertisingId()
            val installReferrer = getInstallReferrer()
            val installReferrerResponse = sendInstallReferrerToServer(installReferrer)
            trySend(Triple(deepLink, advertisingId, installReferrerResponse)).isSuccess

        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Triple(null, "", "")).isSuccess
        } finally {
            close()
        }
    }
}