package com.example.appdistance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdistance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val printTextWithoutScreenButton: Button = findViewById(R.id.button_calculate)

        printTextWithoutScreenButton.setOnClickListener {
            print("true", fromResource(R.raw.printable_text))
        }
    }

    private fun print(showFeedback: String, stringRaw: String) {
        val deeplinkReturnScheme = getString(R.string.scheme_return)

        val uriBuilder = Uri.Builder()
        uriBuilder.authority("print")
        uriBuilder.scheme("printer-app")
        uriBuilder.appendQueryParameter("SHOW_FEEDBACK_SCREEN", showFeedback)
        uriBuilder.appendQueryParameter("SCHEME_RETURN", deeplinkReturnScheme)
        uriBuilder.appendQueryParameter("PRINTABLE_CONTENT", stringRaw)
        startMyActivity(uriBuilder)
    }

    private fun startMyActivity(uriBuilder: Uri.Builder) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = uriBuilder.build()
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.data != null) {
            Log.d("TESTE", intent.data.toString())
            Toast.makeText(this, intent.data.toString(), Toast.LENGTH_LONG).show()
        }
    }
}