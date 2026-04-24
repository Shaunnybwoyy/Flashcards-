package com.example.flashcards

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {

    var tvQuestionNumber: TextView? = null
    var tvStatement: TextView? = null
    var tvFeedback: TextView? = null
    var btnHack: Button? = null
    var btnMyth: Button? = null
    var btnNext: Button? = null

    val questions = QuestionRepository.questions
    var currentIndex = 0
    var score = 0
    var answered = false

    val userAnswers = mutableListOf<Pair<Boolean, Question>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvStatement = findViewById(R.id.tvStatement)
        tvFeedback = findViewById(R.id.tvFeedback)
        btnHack = findViewById(R.id.btnHack)
        btnMyth = findViewById(R.id.btnMyth)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnHack?.setOnClickListener { checkAnswer(true) }
        btnMyth?.setOnClickListener { checkAnswer(false) }

        btnNext?.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                navigateToScore()
            }
        }
    }

    fun loadQuestion() {
        answered = false
        tvFeedback?.visibility = View.INVISIBLE
        btnNext?.visibility = View.GONE
        btnHack?.isEnabled = true
        btnMyth?.isEnabled = true

        btnHack?.setBackgroundColor(ContextCompat.getColor(this, R.color.button_default))
        btnMyth?.setBackgroundColor(ContextCompat.getColor(this, R.color.button_default))

        val q = questions[currentIndex]
        tvQuestionNumber?.text = "Question ${currentIndex + 1} of ${questions.size}"
        tvStatement?.text = q.statement
    }

    fun checkAnswer(userSaidHack: Boolean) {
        if (answered) return
        answered = true

        val q = questions[currentIndex]
        val correct = userSaidHack == q.isHack

        userAnswers.add(Pair(userSaidHack, q))

        if (correct) {
            score++
            tvFeedback?.text = "✅ Correct! That's a real time-saver!"
            tvFeedback?.setTextColor(ContextCompat.getColor(this, R.color.correct_green))
        } else {
            tvFeedback?.text = "❌ Wrong! That's just an urban myth."
            tvFeedback?.setTextColor(ContextCompat.getColor(this, R.color.wrong_red))
        }

        val correctBtn = if (q.isHack) btnHack else btnMyth
        val wrongBtn = if (q.isHack) btnMyth else btnHack
        correctBtn?.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))
        wrongBtn?.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_red))

        btnHack?.isEnabled = false
        btnMyth?.isEnabled = false

        tvFeedback?.visibility = View.VISIBLE
        btnNext?.visibility = View.VISIBLE

        if (currentIndex == questions.size - 1) {
            btnNext?.text = "See Results"
        } else {
            btnNext?.text = "Next"
        }
    }

    fun navigateToScore() {
        val intent = Intent(this, ScoreActivity::class.java).apply {
            putExtra("SCORE", score)
            putExtra("TOTAL", questions.size)
            putExtra("STATEMENTS", questions.map { it.statement }.toTypedArray())
            putExtra("EXPLANATIONS", questions.map { it.explanation }.toTypedArray())
            putExtra("CORRECT_ANSWERS", questions.map { it.isHack }.toBooleanArray())
            putExtra("USER_ANSWERS", userAnswers.map { it.first }.toBooleanArray())
        }
        startActivity(intent)
        finish()
    }
}
