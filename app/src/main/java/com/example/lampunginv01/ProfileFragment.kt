package com.example.lampunginv01

import android.app.Activity
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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lampunginv01.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

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

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        setupAction()
        setupTermsText()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        if (SessionManager.isLoggedIn) {
            binding.layoutGuest.visibility = View.GONE
            binding.layoutLoggedIn.visibility = View.VISIBLE

            // Update Profile UI
            binding.tvProfileName.text = SessionManager.userName ?: "Pengguna"
            
            if (SessionManager.userPhotoUrl != null) {
                Glide.with(this)
                    .load(SessionManager.userPhotoUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile)
                    .into(binding.ivProfilePicture)
            } else {
                binding.ivProfilePicture.setImageResource(R.drawable.ic_profile)
            }

        } else {
            binding.layoutGuest.visibility = View.VISIBLE
            binding.layoutLoggedIn.visibility = View.GONE
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ProfileFragment", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
            Toast.makeText(requireContext(), "Google Sign In Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            SessionManager.isLoggedIn = true
            SessionManager.userName = account.displayName
            SessionManager.userEmail = account.email
            SessionManager.userPhotoUrl = account.photoUrl?.toString()
            
            checkLoginStatus()
            Toast.makeText(requireContext(), "Selamat Datang, ${account.displayName}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            SessionManager.isLoggedIn = false
            SessionManager.hasReported = false
            SessionManager.userName = null
            SessionManager.userEmail = null
            SessionManager.userPhotoUrl = null
            checkLoginStatus()
            Toast.makeText(requireContext(), "Berhasil Keluar Akun", Toast.LENGTH_SHORT).show()
        }
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
            // Simulasi Login Berhasil
            SessionManager.isLoggedIn = true
            SessionManager.userName = "Ratu Berliana" // Default simulation name
            SessionManager.userPhotoUrl = null
            checkLoginStatus()
            Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            signOut()
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Lupa Password diklik", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoogleSignin.setOnClickListener {
            signIn()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(requireContext(), RegisterNameActivity::class.java))
        }

        binding.tvUpgradeAsn.setOnClickListener {
            startActivity(Intent(requireContext(), AsnVerificationActivity::class.java))
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