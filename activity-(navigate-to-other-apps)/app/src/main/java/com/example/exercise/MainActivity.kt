package com.example.exercise

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonMap.setOnClickListener {
            val mapIntent: Intent = Uri.parse(
                "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
            ).let { location ->
                Intent(Intent.ACTION_VIEW, location)
            }
            startActivity(mapIntent)
        }

        buttonWeb.setOnClickListener {
            val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.android.com"))
            startActivity(webIntent)
        }

        buttonPhone.setOnClickListener {
            val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:666666666"))
            startActivity(callIntent)
        }

        buttonMail.setOnClickListener {
            val mailIntent: Intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain" // La intent no tiene un URI, así que declare el tipo MIME "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("jon@example.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment")) // Se puede adjuntar varios elementos pasando una ArrayList de Uris
            }
            startActivity(mailIntent)
        }

        buttonPhoto.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val activities: List<ResolveInfo> = packageManager.queryIntentActivities(
                cameraIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            val isIntentSafe: Boolean = activities.isNotEmpty()

            if(isIntentSafe) {
                startActivityForResult(intent, CODE_REQUEST)
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        when (requestCode) {
            Companion.CODE_REQUEST ->
                if (resultCode == RESULT_OK) {
                    println("Obtenida imagen")
                }
        }
    }

    companion object {
        const val CODE_REQUEST = 1  // The request code
    }
}
