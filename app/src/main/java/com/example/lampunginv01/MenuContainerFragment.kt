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
        const val ARG_INITIAL_TAB = "arg_initial_tab"
        const val TAB_UNGGULAN = 0
        const val TAB_KATEGORI = 1

        fun newInstance(initialTab: Int = TAB_UNGGULAN): MenuContainerFragment {
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

        // Handle padding top for status bar (since this is now a fragment inside MainActivity, 
        // MainActivity might handle some insets, but we want to be safe or adjust if needed)
        ViewCompat.setOnApplyWindowInsetsListener(binding.headerContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top + 40, v.paddingRight, v.paddingBottom)
            insets
        }
        
        setupClickListeners()

        val initialTab = arguments?.getInt(ARG_INITIAL_TAB, TAB_UNGGULAN) ?: TAB_UNGGULAN
        switchTab(initialTab)
    }

    private fun setupClickListeners() {
        binding.btnBackContainer.setOnClickListener {
            // Navigate back to HomeFragment
            // Assuming MainActivity has a method to go back or load Home
            if (activity is MainActivity) {
                (activity as MainActivity).loadFragment(HomeFragment())
                // Also update bottom nav index to 0 (Home)
                (activity as MainActivity).updateBottomNavUI(0)
            }
        }

        binding.tabUnggulan.setOnClickListener {
            switchTab(TAB_UNGGULAN)
        }

        binding.tabKategori.setOnClickListener {
            switchTab(TAB_KATEGORI)
        }
    }

    private fun switchTab(tabIndex: Int) {
        val activeColor = Color.BLACK
        val inactiveColor = Color.DKGRAY

        if (tabIndex == TAB_UNGGULAN) {
            // UI Update for Tab Unggulan Active
            binding.tvTabUnggulan.setTextColor(activeColor)
            binding.tvTabUnggulan.setTypeface(null, Typeface.BOLD)
            binding.indicatorUnggulan.visibility = View.VISIBLE

            binding.tvTabKategori.setTextColor(inactiveColor)
            binding.tvTabKategori.setTypeface(null, Typeface.NORMAL)
            binding.indicatorKategori.visibility = View.INVISIBLE

            // Load Fragment Unggulan
            loadChildFragment(UnggulanFragment())
        } else {
            // UI Update for Tab Kategori Active
            binding.tvTabUnggulan.setTextColor(inactiveColor)
            binding.tvTabUnggulan.setTypeface(null, Typeface.NORMAL)
            binding.indicatorUnggulan.visibility = View.INVISIBLE

            binding.tvTabKategori.setTextColor(activeColor)
            binding.tvTabKategori.setTypeface(null, Typeface.BOLD)
            binding.indicatorKategori.visibility = View.VISIBLE

            // Load Fragment Kategori
            loadChildFragment(KategoriFragment())
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