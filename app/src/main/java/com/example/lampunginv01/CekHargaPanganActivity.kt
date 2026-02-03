package com.example.lampunginv01

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.lampunginv01.databinding.ActivityCekHargaPanganBinding

data class Market(
    val name: String,
    val district: String,
    val city: String,
    val imageRes: Int
)

class CekHargaPanganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCekHargaPanganBinding
    private var currentPage = 0
    private val markets = listOf(
        // Page 1
        Market("Pasar Gintung", "Tanjung Karang Pusat", "Kota Bandar Lampung", R.drawable.gintung),
        Market("Pasar Panjang", "Panjang", "Kota Bandar Lampung", R.drawable.panjang),
        Market("Pasar Way Halim", "Way Halim", "Kota Bandar Lampung", R.drawable.wayhalim),
        Market("Pasar Way Kandis", "Tanjung Senang", "Kota Bandar Lampung", R.drawable.waykandis),
        Market("Tamin", "Tanjung Karang Barat", "Kota Bandar Lampung", R.drawable.tamin),
        Market("Inpres Kalianda", "Kalianda", "Kab. Lampung Selatan", R.drawable.inpreskalianda),
        
        // Page 2
        Market("PS Bandar Jaya", "Terbanggi Besar", "Kab. Lampung Tengah", R.drawable.bjaya),
        Market("PS Sentral", "Kotabumi Selatan", "Kotabumi Selatan", R.drawable.sentral),
        Market("Pasar Liwa", "Balik Bukit", "Kab. Lampung Barat", R.drawable.liwa),
        Market("PS Unit 2 Tulang Bawang", "Banjar Agung", "Kab. Tulang Bawang", R.drawable.tuba),
        Market("PS Kota Agung", "Kota Agung", "Kab. Tanggamus", R.drawable.kotaagung),
        Market("PS Way Jepara", "Way Jepara", "Kab. Lampung Timur", R.drawable.wayjepara)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCekHargaPanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnFilter.setOnClickListener {
            val dialog = com.google.android.material.bottomsheet.BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.layout_filter_bottom_sheet, null)
            
            // Find Views
            val hintKecamatan = view.findViewById<android.view.View>(R.id.tv_hint_kecamatan)
            val listKecamatan = view.findViewById<android.view.View>(R.id.layout_list_kecamatan)
            
            // Kabupaten Chips
            val kabupatenChips = listOf<android.widget.TextView>(
                view.findViewById(R.id.chip_bandar_lampung),
                view.findViewById(R.id.chip_lampung_barat),
                view.findViewById(R.id.chip_tanggamus),
                view.findViewById(R.id.chip_lampung_selatan),
                view.findViewById(R.id.chip_lampung_timur),
                view.findViewById(R.id.chip_lampung_tengah)
            )

            // Kecamatan Chips
            val kecamatanChips = listOf<android.widget.TextView>(
                view.findViewById(R.id.chip_teluk_betung),
                view.findViewById(R.id.chip_bumi_waras),
                view.findViewById(R.id.chip_panjang),
                view.findViewById(R.id.chip_kedamaian),
                view.findViewById(R.id.chip_tanjung_pusat),
                view.findViewById(R.id.chip_tanjung_barat)
            )

            // Listener for Kabupaten
            for (chip in kabupatenChips) {
                chip.setOnClickListener {
                    // Reset all
                    kabupatenChips.forEach { it.setBackgroundResource(R.drawable.bg_white_rounded_50) }
                    // Set selected
                    chip.setBackgroundResource(R.drawable.bg_gray_rounded_50)
                    
                    // Show Kecamatan
                    hintKecamatan.visibility = android.view.View.GONE
                    listKecamatan.visibility = android.view.View.VISIBLE
                    
                    // Update Main Activity Text
                    binding.tvFilterKabupaten.text = chip.text
                }
            }

            // Listener for Kecamatan
            for (chip in kecamatanChips) {
                chip.setOnClickListener {
                    // Reset all
                    kecamatanChips.forEach { it.setBackgroundResource(R.drawable.bg_white_rounded_50) }
                    // Set selected
                    chip.setBackgroundResource(R.drawable.bg_gray_rounded_50)
                    
                    // Update Main Activity Text
                    binding.tvFilterKecamatan.text = chip.text
                }
            }

            dialog.setContentView(view)
            dialog.show()
        }

        binding.btnNext.setOnClickListener {
            if ((currentPage + 1) * 6 < markets.size) {
                currentPage++
                updateUI()
            }
        }

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                updateUI()
            }
        }

        updateUI()
    }

    private fun updateUI() {
        val startIndex = currentPage * 6
        
        // Helper to update a card
        fun updateCard(indexOffset: Int, card: CardView, img: ImageView, name: TextView, loc: TextView, city: TextView) {
            val dataIndex = startIndex + indexOffset
            if (dataIndex < markets.size) {
                val market = markets[dataIndex]
                card.visibility = android.view.View.VISIBLE
                img.setImageResource(market.imageRes)
                name.text = market.name
                loc.text = market.district
                city.text = market.city
            } else {
                card.visibility = android.view.View.INVISIBLE
            }
        }

        updateCard(0, binding.card1, binding.img1, binding.name1, binding.loc1, binding.city1)
        updateCard(1, binding.card2, binding.img2, binding.name2, binding.loc2, binding.city2)
        updateCard(2, binding.card3, binding.img3, binding.name3, binding.loc3, binding.city3)
        updateCard(3, binding.card4, binding.img4, binding.name4, binding.loc4, binding.city4)
        updateCard(4, binding.card5, binding.img5, binding.name5, binding.loc5, binding.city5)
        updateCard(5, binding.card6, binding.img6, binding.name6, binding.loc6, binding.city6)
    }
}
