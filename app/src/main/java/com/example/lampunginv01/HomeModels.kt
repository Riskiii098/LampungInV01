package com.example.lampunginv01

data class MenuModel(
    val id: Int,
    val title: String,
    val iconRes: Int
)

data class BannerModel(
    val imageRes: Int
)

data class TestimonialModel(
    val id: Int,
    val name: String,
    val rating: Float,
    val comment: String
)

data class NewsCategoryModel(
    val id: Int,
    val title: String,
    val iconRes: Int
)

data class RecommendationModel(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)