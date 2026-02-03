package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityAsnOtpVerificationBinding

class AsnOtpVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsnOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsnOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("EMAIL") ?: "nama@gmail.com"
        val subtitle = "Kami telah mengirim kode OTP di <font color='#808080'>ke</font> <b>$email</b>"
        binding.tvSubtitle.text = Html.fromHtml(subtitle, Html.FROM_HTML_MODE_LEGACY)

        binding.btnLanjutkan.setOnClickListener {
            val otp = "${binding.otp1.text}${binding.otp2.text}${binding.otp3.text}${binding.otp4.text}${binding.otp5.text}"
            if (otp.length == 5) {
                val intent = Intent(this, AsnSuccessActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Masukkan kode OTP lengkap", Toast.LENGTH_SHORT).show()
            }
        }
    }
}