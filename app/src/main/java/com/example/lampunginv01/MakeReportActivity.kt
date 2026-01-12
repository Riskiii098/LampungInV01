package com.example.lampunginv01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class MakeReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_make_report)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content) ?: findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<ImageView>(R.id.iv_back)
        btnBack.setOnClickListener {
            finish()
        }

        val rvCategories = findViewById<RecyclerView>(R.id.rv_categories)
        val categories = listOf("Keadaan Darurat", "Kerusakan Infrastruktur", "Keluhan Layanan Publik", "Masalah Kesehatan", "Gangguan Lingkungan")
        val adapter = ReportCategoryAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = adapter

        // Setup All Categories (Expandable)
        val rvAllCategories = findViewById<RecyclerView>(R.id.rv_all_categories)
        val allCategories = listOf(
            ExpandableCategoryAdapter.ExpandableItem(
                "Keadaan Darurat",
                "Situasi mendesak (berkaitan dengan keselamatan jiwa, kesehatan, atau keamanan publik), butuh respon cepat."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Masalah Layanan Kesehatan",
                "Keluhan terkait layanan kesehatan seperti fasilitas medis, rumah sakit, BPJS, ketersediaan ambulans, atau kebutuhan donor darah."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Kerusakan Infrastruktur",
                "Kerusakan atau gangguan pada sarana umum (jalan, jembatan, saluran air, dll)."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Ganggguan Lingkungan",
                "Melaporkan permasalahan lingkungan (penumpukan sampah, pencemaran air atau udara, TPS ilegal, kerusakan ruang terbuka, serta kendala pengelolaan kebersihan dan tata lingkungan)."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Perizinan & Administrasi Usaha",
                "Laporan bagi pelaku usaha atau masyarakat yang mengalami kendala dalam proses perizinan seperti pengajuan NIB, izin tata ruang, layanan DPMPTSP, atau administrasi pertanahan di BPN."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Laporan Kebencanaan",
                "Melaporkan kejadian atau dampak bencana alam dan cuaca ekstrem serta menerima notifikasi peringatan dini yang membutuhkan koordinasi antarinstansi."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Masalah Ketenagakerjaan",
                "Melaporkan atau menanyakan hal terkait dunia kerja meliputi informasi lowongan, magang, pelatihan kerja, konsultasi tenaga kerja, atau sengketa dan kendala pekerjaan."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Permasalahan Transportasi",
                "Berkaitan dengan lalu lintas dan perhubungan seperti rambu rusak, angkutan umum, kemacetan, kecelakaan, atau gangguan fasilitas transportasi."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Laporan Umum Lainnya",
                "Laporan yang tidak termasuk dalam kategori khusus."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Keluhan Layanan Publik",
                "Melaporkan pengalaman tidak memuaskan terhadap pelayanan pemerintahan secara umum, proses birokrasi, aplikasi pemerintah, atau interaksi dengan instansi daerah."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Pajak & Retribusi",
                "Melaporkan masalah yang berkaitan dengan pajak daerah dan pembayaran resmi seperti PBB, pajak kendaraan, retribusi, atau gangguan pada sistem pembayaran online milik pemerintah."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Informasi Tidak Akurat",
                "Melaporkan temuan data atau informasi pemerintah yang keliru, menyesatkan, tidak diperbarui, atau sulit diakses pada layanan seperti JDIH, PPID, harga pangan, dan data terbuka."
            ),
            ExpandableCategoryAdapter.ExpandableItem(
                "Aduan Sosial Masyarakat",
                "Melaporkan pengalaman tidak memuaskan terhadap pelayanan pemerintahan secara umum, proses birokrasi, aplikasi pemerintah, atau interaksi dengan instansi daerah."
            )
        )
        val expandableAdapter = ExpandableCategoryAdapter(allCategories) { categoryName ->
            // Callback ketika item diklik -> Pindah ke halaman Ambil Foto (Langkah 2)
            val intent = android.content.Intent(this, TakePhotoActivity::class.java)
            intent.putExtra("CATEGORY_NAME", categoryName)
            startActivity(intent)
        }
        rvAllCategories.layoutManager = LinearLayoutManager(this)
        rvAllCategories.adapter = expandableAdapter
    }
}