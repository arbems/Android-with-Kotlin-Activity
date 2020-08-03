package com.arbems.dynamicshortcut

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * list of dynamic shortcuts
         */
        addDynamicShortcuts(applicationContext)

        /**
         * augment an existing list of dynamic shortcuts
         */
        augmentDynamicShortcuts(applicationContext)

        /**
         * update an existing list of dynamic shortcuts
         */
        button.setOnClickListener {
            updateDynamicShortcuts(applicationContext)
        }

        /**
         * remove a set of dynamic shortcuts
         */
        button2.setOnClickListener {
            removeAllDynamicShortcuts(applicationContext)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun addDynamicShortcuts(context: Context) {
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val shortcutInfo = ShortcutInfo.Builder(context, "id1")
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(Icon.createWithResource(context, R.drawable.icon_website))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/arbems/")
                )
            )
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcutInfo)
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun augmentDynamicShortcuts(context: Context) {
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val shortcutInfo2 = ShortcutInfo.Builder(context, "id2")
            .setShortLabel("Website 2")
            .setLongLabel("Open the website 2")
            .setIcon(Icon.createWithResource(context, R.drawable.icon_website))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://kotlinlang.org/")
                )
            )
            .build()

        shortcutManager!!.addDynamicShortcuts(listOf(shortcutInfo2))
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun updateDynamicShortcuts(context: Context) {
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val items = (1..10).map { Random().nextInt() % 10 }

        shortcutManager.dynamicShortcuts = items.map {
            ShortcutInfo.Builder(context, it.toString())
                .setShortLabel("Action $it")
                .setLongLabel("Long title for action $it")
                .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                .setIntent(
                    Intent(context, MainActivity::class.java).apply {
                        action = "ACTION_SHORTCUT"
                        title = "Action $it"
                    }
                )
                .build()
        }.take(3)
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun removeAllDynamicShortcuts(context: Context) {
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        shortcutManager!!.removeAllDynamicShortcuts()
    }
}