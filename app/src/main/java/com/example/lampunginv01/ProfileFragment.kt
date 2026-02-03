package com.example.lampunginv01

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lampunginv01.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle System Bar Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top + 40, v.paddingRight, v.paddingBottom)
            insets
        }

        setupAction()
        setupTermsText()
    }

    private fun setupAction() {
        binding.btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                // Show Password
                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.btnTogglePassword.setImageResource(R.drawable.ic_eye_off_outline) 
            } else {
                // Hide Password
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.btnTogglePassword.setImageResource(R.drawable.ic_eye_outline)
            }
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }

        binding.btnLogin.setOnClickListener {
            (activity as? MainActivity)?.let { mainActivity ->
                mainActivity.loadFragment(HomeFragment())
                mainActivity.updateBottomNavUI(0)
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Lupa Password diklik", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoogleSignin.setOnClickListener {
            Toast.makeText(requireContext(), "Masuk dengan Google diklik", Toast.LENGTH_SHORT).show()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(requireContext(), RegisterNameActivity::class.java))
        }
    }

    private fun setupTermsText() {
        val fullText = "Dengan masuk ke aplikasi Lampung-In, kamu telah membaca dan menyetujui Syarat dan Ketentuan dan Kebijakan Privasi kami"
        val spannableString = SpannableString(fullText)

        // Syarat dan Ketentuan
        val termsStart = fullText.indexOf("Syarat dan Ketentuan")
        val termsEnd = termsStart + "Syarat dan Ketentuan".length
        
        // Kebijakan Privasi
        val privacyStart = fullText.indexOf("Kebijakan Privasi")
        val privacyEnd = privacyStart + "Kebijakan Privasi".length

        val clickableSpanTerms = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "Syarat dan Ketentuan", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = resources.getColor(android.R.color.black, null) // Atau warna lain
                ds.typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
            }
        }

        val clickableSpanPrivacy = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "Kebijakan Privasi", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = resources.getColor(android.R.color.black, null)
                ds.typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
            }
        }

        spannableString.setSpan(clickableSpanTerms, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpanPrivacy, privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTerms.text = spannableString
        binding.tvTerms.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}