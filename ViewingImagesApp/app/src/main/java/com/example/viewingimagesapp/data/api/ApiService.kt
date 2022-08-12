package com.example.viewingimagesapp.data.api

import com.example.viewingimagesapp.model.Images
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/photos?per_page=20&client_id=dFxE9x4usF89AfGoRkSmoCu5i3SQ_7Fkc6YLeHZs9Vg")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<Images>
}