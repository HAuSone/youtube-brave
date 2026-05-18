package com.youtubebrave.app

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val youtubeUrl = "https://www.youtube.com"
        val bravePkg  = "com.brave.browser"

        val isInstalled = try {
            packageManager.getPackageInfo(bravePkg, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

        if (isInstalled) {
            // Ouvre directement Brave sur YouTube
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
                setPackage(bravePkg)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        } else {
            // Brave non installé → navigateur par défaut + message
            Toast.makeText(this, "Brave non trouvé, ouverture dans le navigateur par défaut", Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
            startActivity(intent)
        }

        // Ferme immédiatement l'activité → pas de retour sur un écran vide
        finish()
    }
}
