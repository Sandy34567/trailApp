package com.example.trailapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trailapp.databinding.ActivityDetails2Binding



class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetails2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewWikipediaLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Amber_Fort"))
            startActivity(intent)
        }
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
        binding.buyNowButton.setOnClickListener {
            val rewardDialog = RewardDialogFragment()
            rewardDialog.show(supportFragmentManager, "RewardDialog")
        }

    }
}