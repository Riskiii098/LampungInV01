package com.example.lampunginv01

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lampunginv01.databinding.FragmentMenuContainerBinding

class MenuContainerFragment : Fragment() {

    private var _binding: FragmentMenuContainerBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ARG_INITIAL_TAB = "arg_initial_tab" // Deprecated but kept to avoid breaking calls
        const val TAB_UNGGULAN = 0
        const val TAB_KATEGORI = 1

        fun newInstance(initialTab: Int = TAB_KATEGORI): MenuContainerFragment {
            val fragment = MenuContainerFragment()
            val args = Bundle()
            args.putInt(ARG_INITIAL_TAB, initialTab)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle padding top for status bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.headerContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top + 80, v.paddingRight, v.paddingBottom)
            insets
        }
        
        setupClickListeners()

        // Always load KategoriFragment
        loadChildFragment(KategoriFragment())
    }

    private fun setupClickListeners() {
        binding.btnBackContainer.setOnClickListener {
            // Navigate back to HomeFragment
            if (activity is MainActivity) {
                (activity as MainActivity).loadFragment(HomeFragment())
                // Also update bottom nav index to 0 (Home)
                (activity as MainActivity).updateBottomNavUI(0)
            }
        }
    }

    private fun loadChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(binding.contentContainer.id, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}