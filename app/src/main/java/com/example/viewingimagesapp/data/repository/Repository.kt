package com.example.viewingimagesapp.data.repository

import com.example.viewingimagesapp.data.api.RetrofitInstance
import com.example.viewingimagesapp.model.Images
import retrofit2.Response

class Repository {
    suspend fun getIm(query: String, page: Int): Response<Images> {
        return RetrofitInstance.api.getImages(query, page)
    }
}