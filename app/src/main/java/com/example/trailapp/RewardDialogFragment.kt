package com.example.trailapp
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieDrawable
import com.example.trailapp.databinding.FragmentRewardDialogBinding



class RewardDialogFragment() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentRewardDialogBinding.inflate(layoutInflater)
        binding.close.setOnClickListener {
            dismiss()
        }

        // Set the animation and make it loop continuously
        binding.animationView.apply {
            setAnimation(R.raw.cart_animation)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }




        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create().apply {
                // Set transparent background for the dialog window
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
    }
}