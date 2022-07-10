package com.healthypocket.service

import com.healthypocket.model.Medicamento
import retrofit2.Call
import retrofit2.http.*

interface MedicamentoService {
    @GET("medicamento/")
    fun getMedicamentos() : Call<List<Medicamento>>

    @PUT("medicamento/{id}/")
    fun putMedicamentos(@Path("id") string: String, @Body medicamento: Medicamento): Call<Medicamento>

    @POST("medicamento/")
    fun postMedicamentos(@Body medicamento: Medicamento) : Call<Medicamento>

    @DELETE("medicamento/{id}/")
    fun deleteMedicamentos(@Path("id") string: String): Call<Medicamento>

}