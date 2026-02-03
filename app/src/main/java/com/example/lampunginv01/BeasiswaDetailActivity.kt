package com.example.lampunginv01

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityBeasiswaDetailBinding
import com.example.lampunginv01.databinding.DialogDetailBeasiswaBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BeasiswaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeasiswaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeasiswaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle System Bar Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.cardBeasiswa1.setOnClickListener {
            showDetailDialog()
        }
    }

    private fun showDetailDialog() {
        val dialogBinding = DialogDetailBeasiswaBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogBinding.btnCloseDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnDaftar.setOnClickListener {
            // Do nothing as requested
        }

        dialog.show()
    }
}