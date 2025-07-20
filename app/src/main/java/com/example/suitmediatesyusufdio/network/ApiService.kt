package com.example.suitmediatesyusufdio.network

import com.example.suitmediatesyusufdio.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UserResponse>
}

data class UserResponse(val data: List<User>)
