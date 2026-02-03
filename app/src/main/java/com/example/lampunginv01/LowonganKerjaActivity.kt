package com.example.lampunginv01

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityLowonganKerjaBinding
import com.example.lampunginv01.databinding.DialogDetailLowonganBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LowonganKerjaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLowonganKerjaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLowonganKerjaBinding.inflate(layoutInflater)
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

        binding.cardLowongan1.setOnClickListener {
            showDetailDialog()
        }
    }

    private fun showDetailDialog() {
        val dialogBinding = DialogDetailLowonganBinding.inflate(LayoutInflater.from(this))
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