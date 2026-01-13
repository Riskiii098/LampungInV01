package com.example.lampunginv01

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.lampunginv01.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    // Handler untuk Auto Slide
    private val sliderHandler = Handler(Looper.getMainLooper())
    private lateinit var sliderRunnable: Runnable
    private var currentAnimator: ValueAnimator? = null

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        // 1. Handling System Bar (Agar tidak tertutup Sinyal/Baterai)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContent) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Tambahkan padding atas sesuai tinggi status bar + extra space
            v.setPadding(v.paddingLeft, systemBars.top + 40, v.paddingRight, v.paddingBottom)
            insets
        }

        setupMenuGrid()
        setupBannerSlider()
        setupTestimonials()
        setupNewsCategories()
        setupRecommendations()
    }

    private fun setupMenuGrid() {
        // Data Dummy (5 Item agar pas 1 baris)
        val menuList = listOf(
            MenuModel(1, "Laporan", R.drawable.ic_lapor),
            MenuModel(2, "Berita", R.drawable.ic_berita),
            MenuModel(3, "Kontak Darurat", R.drawable.ic_kontakdarurat),
            MenuModel(4, "Pajak", R.drawable.ic_pajak),
            MenuModel(5, "Lainnya", R.drawable.ic_lainnya) // Gunakan ikon titik-titik (ic_lainnya)
        )

        val menuAdapter = MenuAdapter(menuList) { selectedMenu ->
            if (selectedMenu.title == "Lainnya") {
                if (activity is MainActivity) {
                    (activity as MainActivity).loadFragment(MenuContainerFragment.newInstance(MenuContainerFragment.TAB_UNGGULAN))
                }
            } else if (selectedMenu.title == "Laporan") {
                val intent = Intent(requireContext(), MakeReportActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Buka: ${selectedMenu.title}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvFeatures.apply {
            // PENTING: Ubah spanCount jadi 5 untuk tampilan horizontal (5 item per baris)
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = menuAdapter
            // Tambahkan ini agar grid tidak bisa discroll sendiri (menyatu dengan scroll utama)
            isNestedScrollingEnabled = false
        }
    }

    private fun setupBannerSlider() {
        // Dummy Data Banner (4 item)
        val bannerList = listOf(
            BannerModel(R.drawable.banner1), // Ganti dengan gambar banner asli nanti
            BannerModel(R.drawable.banner1),
            BannerModel(R.drawable.banner1),
            BannerModel(R.drawable.banner1)
        )

        val bannerAdapter = BannerAdapter(bannerList)
        binding.vpSlider.adapter = bannerAdapter

        // Set teks indikator awal
        updateIndicatorText(0, bannerList.size)

        // Callback saat slide berubah (Manual atau Otomatis)
        binding.vpSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicatorText(position, bannerList.size)

                // Reset timer auto slide agar tidak "lompat" saat user geser manual
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 4000) // 4 Detik
            }
        })

        // Definisi Runnable untuk Auto Slide dengan Animasi Slow
        sliderRunnable = Runnable {
            // Cek lifecycle agar aman
            if (!isAdded || view == null) return@Runnable
            // Cek jika sedang fake dragging agar tidak crash
            if (binding.vpSlider.isFakeDragging) return@Runnable

            val currentItem = binding.vpSlider.currentItem
            val nextItem = if (currentItem == bannerList.size - 1) 0 else currentItem + 1

            if (nextItem == 0) {
                // Jika kembali ke awal, gunakan scroll biasa
                binding.vpSlider.setCurrentItem(0, true)
            } else {
                // Gunakan Fake Drag untuk animasi slide yang lebih pelan (custom duration)
                if (!binding.vpSlider.beginFakeDrag()) return@Runnable
                
                val width = binding.vpSlider.width.toFloat()
                
                // Durasi animasi disesuaikan menjadi 1000ms (1 detik) - sedikit lebih cepat dari sebelumnya (1500ms)
                val animator = ValueAnimator.ofFloat(0f, width)
                currentAnimator = animator
                animator.duration = 1000 
                animator.interpolator = AccelerateDecelerateInterpolator()
                
                var oldVal = 0f
                animator.addUpdateListener { animation ->
                    // Cek binding sebelum akses
                    if (!isAdded || view == null) {
                        currentAnimator?.cancel()
                        return@addUpdateListener
                    }
                    val currentValue = animation.animatedValue as Float
                    val delta = currentValue - oldVal
                    // Geser viewpager dengan fake drag (negatif untuk ke kanan/item berikutnya)
                    try {
                        binding.vpSlider.fakeDragBy(-delta)
                    } catch (e: Exception) {
                        // Ignore potential errors during destruction
                    }
                    oldVal = currentValue
                }
                
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        currentAnimator = null
                        if (!isAdded || view == null) return
                        // Akhiri fake drag agar snap ke halaman baru
                        if (binding.vpSlider.isFakeDragging) {
                            binding.vpSlider.endFakeDrag()
                        }
                    }
                    
                    override fun onAnimationCancel(animation: Animator) {
                        super.onAnimationCancel(animation)
                        currentAnimator = null
                        if (isAdded && view != null && binding.vpSlider.isFakeDragging) {
                            binding.vpSlider.endFakeDrag()
                        }
                    }
                })
                animator.start()
            }
        }
    }

    private fun setupTestimonials() {
        val testimonialList = listOf(
            TestimonialModel(1, "Budi Santoso", 5f, "Aplikasi ini sangat membantu warga Lampung dalam mengurus dokumen kependudukan."),
            TestimonialModel(2, "Siti Aminah", 4f, "Fitur berita sangat up-to-date, saya jadi tahu info terkini di daerah saya."),
            TestimonialModel(3, "Rahmat Hidayat", 5f, "Pelayanan cepat dan mudah diakses. Terima kasih pemerintah provinsi!"),
            TestimonialModel(4, "Dewi Lestari", 5f, "Desain aplikasinya bagus dan mudah digunakan oleh orang tua saya.")
        )

        val testimonialAdapter = TestimonialAdapter(testimonialList)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        binding.rvTestimonials.apply {
            this.layoutManager = layoutManager
            this.adapter = testimonialAdapter
            
            // Tambahkan SnapHelper agar berhenti pas di tengah item
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
            
            // Tambahkan ScrollListener untuk efek zoom
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    
                    val centerX = width / 2f
                    
                    for (i in 0 until childCount) {
                        val child = getChildAt(i) ?: continue
                        val childCenterX = (child.left + child.right) / 2f
                        val distanceFromCenter = Math.abs(centerX - childCenterX)
                        val scale = 1f - (distanceFromCenter / width) * 0.2f // Scale factor
                        
                        // Batasi scale minimal agar tidak terlalu kecil
                        val finalScale = Math.max(0.9f, scale)
                        
                        child.scaleX = finalScale
                        child.scaleY = finalScale
                        child.alpha = Math.max(0.8f, finalScale) // Opsional: transparansi
                    }
                }
            })
        }
    }

    private fun setupNewsCategories() {
        val categoryList = listOf(
            NewsCategoryModel(1, "Kesehatan", R.drawable.ic_kt5),
            NewsCategoryModel(2, "Darurat & Keamanan", R.drawable.ic_kt2),
            NewsCategoryModel(3, "Aspirasi & Pengaduan", R.drawable.ic_kt1),
            NewsCategoryModel(4, "Fasilitas Umum", R.drawable.ic_kt7),
            NewsCategoryModel(5, "Administrasi Kependudukan", R.drawable.ic_kt3),
            NewsCategoryModel(6, "Semua Kategori", R.drawable.kt_lainnya)
        )

        val categoryAdapter = NewsCategoryAdapter(categoryList) { category ->
            if (category.title == "Semua Kategori") {
                if (activity is MainActivity) {
                    (activity as MainActivity).loadFragment(MenuContainerFragment.newInstance(MenuContainerFragment.TAB_KATEGORI))
                }
            } else {
                Toast.makeText(requireContext(), "Kategori: ${category.title}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvNewsCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2) // 2 kolom (kiri-kanan)
            adapter = categoryAdapter
            isNestedScrollingEnabled = false // Agar scroll menyatu dengan scrollview utama
        }
    }

    private fun setupRecommendations() {
        val recommendationList = listOf(
            RecommendationModel(
                1, 
                "Pengguna Baru",
                "Cek fitur unggulan, biar kamu makin tahu.",
                R.drawable.banner2 
            ),
            RecommendationModel(
                2, 
                "Pendatang Baru di Lampung",
                "Hai Sekelik, Masih baru di Lampung? Fitur ini bisa bantu kamu loh",
                R.drawable.banner2 
            ),
            RecommendationModel(
                3, 
                "Eksplore Lampung",
                "Mau tahu destinasi liburan di Lampung? Cek di sini",
                R.drawable.banner2 
            )
        )

        val recommendationAdapter = RecommendationAdapter(recommendationList) { item ->
             Toast.makeText(requireContext(), "Rekomendasi: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvRecommendations.apply {
            layoutManager = LinearLayoutManager(requireContext()) // Vertikal
            adapter = recommendationAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun updateIndicatorText(position: Int, total: Int) {
        val current = position + 1
        binding.tvIndicator.text = "$current/$total"
    }

    override fun setupAction() {
        // Handle action global lain jika ada
    }

    override fun setupObserver() {
        // Nanti hubungkan ke ViewModel disini
    }

    override fun onResume() {
        super.onResume()
        // Mulai Auto Slide saat layar aktif
        sliderHandler.postDelayed(sliderRunnable, 4000)
    }

    override fun onPause() {
        super.onPause()
        // Stop Auto Slide saat layar tidak aktif (Hemat Baterai)
        sliderHandler.removeCallbacks(sliderRunnable)
        currentAnimator?.cancel()
    }
}