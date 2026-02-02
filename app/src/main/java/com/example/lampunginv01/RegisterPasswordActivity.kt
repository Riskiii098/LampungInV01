package com.example.lampunginv01

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.lampunginv01.databinding.ActivityRegisterPasswordBinding

class RegisterPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordVisibility(binding.etPassword)
        setupPasswordVisibility(binding.etConfirmPassword)

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, RegisterPhoneActivity::class.java))
        }
    }

    private fun setupPasswordVisibility(editText: EditText) {
        editText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (editText.right - editText.compoundPaddingRight)) {
                    val selection = editText.selectionEnd
                    if (editText.transformationMethod is PasswordTransformationMethod) {
                        editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_eye_outline, 0)
                    } else {
                        editText.transformationMethod = PasswordTransformationMethod.getInstance()
                        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_eye_off_outline, 0)
                    }
                    editText.setSelection(selection)
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
}