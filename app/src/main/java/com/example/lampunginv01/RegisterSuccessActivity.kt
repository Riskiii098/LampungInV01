package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityRegisterSuccessBinding

class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccessBinding
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bold email text
        val description = "Kami telah mengirimkan link verifikasi melalui email kamu <b>username@gmail.com</b>"
        binding.tvDesc.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)

        binding.btnClose.setOnClickListener {
            // Simpan status login di memori saja
            SessionManager.isLoggedIn = true

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("TARGET_TAB", 4) // Target ke tab Profile
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.btnResend.setOnClickListener {
            // Logic to resend email
            startTimer()
        }

        startTimer()
    }

    private fun startTimer() {
        binding.btnResend.isEnabled = false
        binding.btnResend.alpha = 0.5f
        
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(300000, 1000) { // Changed to 5 minutes (05:00) as per example or keep 60s? Example was (04:59) so 5 mins.
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val timeString = String.format("(%02d:%02d)", minutes, seconds)
                binding.btnResend.text = "Kirim Ulang Email Aktivasi $timeString"
            }

            override fun onFinish() {
                binding.btnResend.text = "Kirim Ulang Email Aktivasi"
                binding.btnResend.isEnabled = true
                binding.btnResend.alpha = 1.0f
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}