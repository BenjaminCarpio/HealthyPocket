package com.healthypocket.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {

    val gson = GsonBuilder().setLenient().create()
    val _BASEURL = "https://healthypocket-ys2do.ondigitalocean.app/api/"
    private val client = Retrofit.Builder()
        .baseUrl(_BASEURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getClient(): Retrofit{
        return client
    }

    val client2: OkHttpClient = OkHttpClient.Builder().apply{
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        this.addInterceptor(interceptor)
    }.build()
    fun getClient2(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(_BASEURL)
            .client(client2)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}