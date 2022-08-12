package com.example.viewingimagesapp.model

data class Images(
    val results: List<ImagesItem>,
    val total: Int,
    val total_pages: Int
)