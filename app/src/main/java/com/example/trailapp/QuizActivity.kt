package com.example.trailapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trailapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOption1.setOnClickListener { checkAnswer("Option 1") }
        binding.buttonOption2.setOnClickListener { checkAnswer("Option 2") }
        binding.buttonOption3.setOnClickListener { checkAnswer("Option 3") }
        binding.buttonOption4.setOnClickListener { checkAnswer("Option 4") }
    }

    private fun checkAnswer(answer: String) {

        val rewardDialog = RewardDialogFragment()
        rewardDialog.show(supportFragmentManager, "RewardDialog")
    }
    }
