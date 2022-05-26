package com.example.book.network

import com.example.book.data.PotterBook
import com.example.book.data.PotterBookItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://hp-api.herokuapp.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookServiceApi{
    @GET("characters/staff")
    suspend fun getBook() : List<PotterBookItem>
}

object BookApi{
    val retrofitBookApi : BookServiceApi by lazy {
        retrofit.create(BookServiceApi::class.java)
    }
}