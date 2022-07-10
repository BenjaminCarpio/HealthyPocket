package com.healthypocket.service

import com.healthypocket.model.Examen

import retrofit2.Call
import retrofit2.http.*

interface ExamenService {
    @GET("examen/")
    fun getExamenes() : Call<List<Examen>>

    @PUT("examen/{id}/")
    fun putExamen(@Path("id") string: String, @Body ex: Examen): Call<Examen>

    @POST("examen/")
    fun postExamen(@Body ex: Examen) : Call<Examen>

    @DELETE("examen/{id}/")
    fun deleteExamen(@Path("id") string: String): Call<Examen>

}