package com.example.trailapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trailapp.databinding.ActivityDetails2Binding
import com.example.trailapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val places = listOf(
            "Hawa Mahal",
            "Jal Mahal",
            "Jantar Mantar",
            "Albert Hall",
            "Birla Mandir",
            "Fresco Painting",
            "Amer Fort ",
            "Jal Mahal",

            )
        val drawables = listOf(
            R.drawable.hawa_mahal,
            R.drawable.jal_mahal,
            R.drawable.jantar_mantar,
            R.drawable.albert_hall,
            R.drawable.birla_mandir,
            R.drawable.fresco_painting,
            R.drawable.amer_fort_entrance,
            R.drawable.jal_mahal)
        val ratings = listOf(
            4.5f,
            4.0f,
            3.5f,
            4.2f,
            4.8f,
            4.3f,
            4.7f,
            4.0f

        )
        val adapter = PlaceAdapter(this,places, drawables, ratings)
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview.adapter = adapter
        binding.picImg.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
        binding.buyNowButton.setOnClickListener {
            val rewardDialog = RewardDialogFragment()
            rewardDialog.show(supportFragmentManager, "RewardDialog")
        }
    }
}