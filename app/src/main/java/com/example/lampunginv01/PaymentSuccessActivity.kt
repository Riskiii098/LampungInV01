package com.example.lampunginv01

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lampunginv01.databinding.ActivityPaymentSuccessBinding

class PaymentSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        
        binding.root.post {
            startAnimation()
        }
    }

    private fun setupUI() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnFinish.setOnClickListener {
            val intent = Intent(this, PaymentReceiptActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish success screen so back button from receipt doesn't go back here
        }
    }

    private fun startAnimation() {
        // 1. Initial State: All hidden (scaled to 0)
        binding.imgLeft.scaleX = 0f
        binding.imgLeft.scaleY = 0f
        
        binding.imgRight.scaleX = 0f
        binding.imgRight.scaleY = 0f
        
        binding.imgCenter.scaleX = 0f
        binding.imgCenter.scaleY = 0f

        // Initial state for decorative dots
        binding.dotYellow1.scaleX = 0f
        binding.dotYellow1.scaleY = 0f
        binding.dotYellow2.scaleX = 0f
        binding.dotYellow2.scaleY = 0f
        binding.dotGreen1.scaleX = 0f
        binding.dotGreen1.scaleY = 0f
        binding.dotGreen2.scaleX = 0f
        binding.dotGreen2.scaleY = 0f
        binding.dotYellow3.scaleX = 0f
        binding.dotYellow3.scaleY = 0f

        val duration = 400L
        val interpolator = OvershootInterpolator(1.5f)

        // 2. Define Animations (Pop Up)
        
        // Step 1: Circle 2 (Left) + Left Dots (Yellow 1, Yellow 2)
        val leftScaleX = ObjectAnimator.ofFloat(binding.imgLeft, "scaleX", 0f, 1f)
        val leftScaleY = ObjectAnimator.ofFloat(binding.imgLeft, "scaleY", 0f, 1f)
        
        val dotY1ScaleX = ObjectAnimator.ofFloat(binding.dotYellow1, "scaleX", 0f, 1f)
        val dotY1ScaleY = ObjectAnimator.ofFloat(binding.dotYellow1, "scaleY", 0f, 1f)
        val dotY2ScaleX = ObjectAnimator.ofFloat(binding.dotYellow2, "scaleX", 0f, 1f)
        val dotY2ScaleY = ObjectAnimator.ofFloat(binding.dotYellow2, "scaleY", 0f, 1f)

        val leftAnim = AnimatorSet()
        leftAnim.play(leftScaleX).with(leftScaleY)
            .with(dotY1ScaleX).with(dotY1ScaleY)
            .with(dotY2ScaleX).with(dotY2ScaleY)
        leftAnim.duration = duration
        leftAnim.interpolator = interpolator

        // Step 2: Circle 3 (Right) + Right Dots (Green 1, Green 2)
        val rightScaleX = ObjectAnimator.ofFloat(binding.imgRight, "scaleX", 0f, 1f)
        val rightScaleY = ObjectAnimator.ofFloat(binding.imgRight, "scaleY", 0f, 1f)

        val dotG1ScaleX = ObjectAnimator.ofFloat(binding.dotGreen1, "scaleX", 0f, 1f)
        val dotG1ScaleY = ObjectAnimator.ofFloat(binding.dotGreen1, "scaleY", 0f, 1f)
        val dotG2ScaleX = ObjectAnimator.ofFloat(binding.dotGreen2, "scaleX", 0f, 1f)
        val dotG2ScaleY = ObjectAnimator.ofFloat(binding.dotGreen2, "scaleY", 0f, 1f)

        val rightAnim = AnimatorSet()
        rightAnim.play(rightScaleX).with(rightScaleY)
            .with(dotG1ScaleX).with(dotG1ScaleY)
            .with(dotG2ScaleX).with(dotG2ScaleY)
        rightAnim.duration = duration
        rightAnim.interpolator = interpolator

        // Step 3: Circle 1 (Center/Top) + Top Center Dot (Yellow 3)
        val centerScaleX = ObjectAnimator.ofFloat(binding.imgCenter, "scaleX", 0f, 1f)
        val centerScaleY = ObjectAnimator.ofFloat(binding.imgCenter, "scaleY", 0f, 1f)

        val dotY3ScaleX = ObjectAnimator.ofFloat(binding.dotYellow3, "scaleX", 0f, 1f)
        val dotY3ScaleY = ObjectAnimator.ofFloat(binding.dotYellow3, "scaleY", 0f, 1f)

        val centerAnim = AnimatorSet()
        centerAnim.play(centerScaleX).with(centerScaleY)
            .with(dotY3ScaleX).with(dotY3ScaleY)
        centerAnim.duration = duration
        centerAnim.interpolator = interpolator

        // 3. Play Sequentially
        val finalSet = AnimatorSet()
        // Left -> Right -> Center
        finalSet.play(leftAnim).before(rightAnim)
        finalSet.play(rightAnim).before(centerAnim)
        
        finalSet.start()
    }
}