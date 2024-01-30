package com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

class WebJunior(private val activity: AppCompatActivity) : WebChromeClient() {

    private var uploadMessage: ValueCallback<Array<Uri>>? = null
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null
    init {
        setupActivityResult()
    }
    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        if (uploadMessage != null) {
            uploadMessage?.onReceiveValue(null)
            uploadMessage = null
        }
        uploadMessage = filePathCallback
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        activityResultLauncher?.launch(Intent.createChooser(intent, "File Browser"))

        return true
    }
    override fun onPermissionRequest(request: PermissionRequest) {
        request.grant(request.resources)
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf<String>( android.Manifest.permission.CAMERA ), 1);
        } else {

        }
    }

    private fun setupActivityResult() {
        activityResultLauncher = activity.activityResultRegistry.register(
            "UploadFile",
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                if (result.data != null) {
                    val uri: Uri? = result.data?.data
                    if (uri != null && uploadMessage != null) {
                        uploadMessage?.onReceiveValue(arrayOf(uri))
                        uploadMessage = null
                    }
                }
            } else {
                uploadMessage?.onReceiveValue(null)
                uploadMessage = null
            }
        }
    }


}
