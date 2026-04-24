package com.example.flashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        
        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvFeedback = findViewById<TextView>(R.id.tvScoreFeedback)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)

        tvScore.text = "You scored $score out of $total!"

        tvFeedback.text = when {
            score == total -> "🏆 Master Hacker! You know your stuff!"
            score >= total * 0.7 -> "🌟 Great job! You're hack-savvy!"
            score >= total * 0.4 -> "👍 Keep practising! You're getting there."
            else -> "🔒 Stay Safe Online! Brush up on those life hacks."
        }

        btnReview.setOnClickListener {
            val reviewIntent = Intent(this, ReviewActivity::class.java).apply {
                putExtra("STATEMENTS", intent.getStringArrayExtra("STATEMENTS"))
                putExtra("EXPLANATIONS", intent.getStringArrayExtra("EXPLANATIONS"))
                putExtra("CORRECT_ANSWERS", intent.getBooleanArrayExtra("CORRECT_ANSWERS"))
                putExtra("USER_ANSWERS", intent.getBooleanArrayExtra("USER_ANSWERS"))
            }
            startActivity(reviewIntent)
        }

        btnPlayAgain.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(mainIntent)
            finish()
        }
    }
}
