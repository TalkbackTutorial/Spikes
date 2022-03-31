package com.example.texttospeech

import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.ENGLISH // We need to detect what language the user is using...?
                try {
                    val systemPitch = Settings.Secure.getInt(contentResolver, Settings.Secure.TTS_DEFAULT_PITCH)
                    val systemRate = Settings.Secure.getInt(contentResolver, Settings.Secure.TTS_DEFAULT_RATE)
                    textToSpeech.setPitch(systemPitch/100F)
                    textToSpeech.setSpeechRate(systemRate/100F)
                } catch (e: SettingNotFoundException) { /* Default value are set */ }
            }
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val text = findViewById<TextView>(R.id.text).text.toString()
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            } else {
                // tts1 ID is required in SDK Lollipop and above
                // TextToSpeech.QUEUE_FLUSH replaces the current speech with the new one
                // TextToSpeech.QUEUE_ADD adds the speech to the queue
                textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, "tts1")
            }
        }
    }

    override fun onPause() {
        textToSpeech.stop()
        super.onPause()
    }

    override fun onDestroy() {
        textToSpeech.shutdown()
        super.onDestroy()
    }
}