package com.healthypocket.service

import com.healthypocket.model.CitaMedica

import retrofit2.Call
import retrofit2.http.*

interface CitasService {
    @GET("cita/")
    fun getCitas() : Call<List<CitaMedica>>

    @PUT("cita/{id}/")
    fun putCitas(@Path("id") string: String, @Body cita: CitaMedica): Call<CitaMedica>

    @POST("cita/")
    fun postCitas(@Body cita: CitaMedica) : Call<CitaMedica>

    @DELETE("cita/{id}/")
    fun deleteCitas(@Path("id") string: String): Call<CitaMedica>

}