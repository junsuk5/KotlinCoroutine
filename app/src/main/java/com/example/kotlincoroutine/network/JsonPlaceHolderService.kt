package com.example.kotlincoroutine.network

import com.example.kotlincoroutine.models.Post
import retrofit2.http.GET



interface JsonPlaceHolderService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    // 비동기 메서드
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}