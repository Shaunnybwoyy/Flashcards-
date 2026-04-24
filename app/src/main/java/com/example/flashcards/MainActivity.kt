package com.example.flashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeMessage = findViewById<TextView>(R.id.tvWelcomeMessage)
        val startButton = findViewById<Button>(R.id.btnStart)

        welcomeMessage.text = getString(R.string.welcome_message)

        startButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}
