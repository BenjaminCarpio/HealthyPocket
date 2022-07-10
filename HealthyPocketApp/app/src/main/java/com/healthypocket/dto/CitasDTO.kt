package com.healthypocket.dto

import android.util.Log
import com.healthypocket.api.ServiceBuilder
import com.healthypocket.model.CitaMedica
import com.healthypocket.service.CitasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitasDTO {

    private var citasData = mutableListOf<CitaMedica>()

    fun getCitas(cb: (R:List<CitaMedica>)->Unit){
        val apiBuilder = ServiceBuilder().getClient().create(CitasService::class.java)
        apiBuilder.getCitas().enqueue(
            object : Callback<List<CitaMedica>> {

                override fun onResponse(call: Call<List<CitaMedica>>, response: Response<List<CitaMedica>>){
                    cb(response.body()!!)
                    citasData.addAll(response.body()!!)
                    Log.d("jej",citasData.toString())
                    Log.e("GET: success","c:")
                }
                override fun onFailure(call: Call<List<CitaMedica>>, t: Throwable) {
                    Log.e("GET: failure",":c")
                    Log.d("Error:", t.message.toString())
                }
            }
        )
    }

    fun postCitas(cita: CitaMedica, onResult: (CitaMedica?) -> Unit){

        val apiBuilder = ServiceBuilder().getClient().create(CitasService::class.java)

        apiBuilder.postCitas(cita).enqueue(
            object : Callback<CitaMedica> {

                override fun onFailure(call: Call<CitaMedica>, t: Throwable) {
                    Log.e("GET: failure",":c")
                    Log.d("Error:", t.message.toString())
                    onResult(null)
                }

                override fun onResponse( call: Call<CitaMedica>, response: Response<CitaMedica>) {
                    Log.e("GET: success","c:")
                    val addedCita = response.body()
                    onResult(addedCita)
                }
            }
        )
    }

    fun putCita(id: String, cita: CitaMedica){

        val apiBuilder = ServiceBuilder().getClient().create(CitasService::class.java)

        apiBuilder.putCitas(id, cita).enqueue(
            object : Callback<CitaMedica> {

                override fun onFailure(call: Call<CitaMedica>, t: Throwable) {
                    Log.e("PUT: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<CitaMedica>, response: Response<CitaMedica>) {
                    Log.e("PUT: success","c:")
                }
            }
        )

    }

    fun deleteCita(id: String){

        val apiBuilder = ServiceBuilder().getClient().create(CitasService::class.java)

        apiBuilder.deleteCitas(id).enqueue(
            object : Callback<CitaMedica> {

                override fun onFailure(call: Call<CitaMedica>, t: Throwable) {
                    Log.e("DELETE: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<CitaMedica>, response: Response<CitaMedica>) {
                    Log.e("DELETE: success","c:")
                }
            }
        )

    }



}