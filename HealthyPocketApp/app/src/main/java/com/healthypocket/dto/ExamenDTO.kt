package com.healthypocket.dto

import android.util.Log
import com.healthypocket.api.ServiceBuilder
import com.healthypocket.model.Examen
import com.healthypocket.service.ExamenService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExamenDTO {

    private var examenData = mutableListOf<Examen>()

    fun getExamenes(cb: (R:List<Examen>)->Unit){
        val apiBuilder = ServiceBuilder().getClient().create(ExamenService::class.java)
        apiBuilder.getExamenes().enqueue(
            object : Callback<List<Examen>> {

                override fun onResponse(call: Call<List<Examen>>, response: Response<List<Examen>>){
                    cb(response.body()!!)
                    examenData.addAll(response.body()!!)
                    Log.d("jej",examenData.toString())
                    Log.e("GET: success","c:")
                }
                override fun onFailure(call: Call<List<Examen>>, t: Throwable) {
                    Log.e("GET: failure",":c")
                    Log.d("Error:", t.message.toString())
                }
            }
        )
    }

    fun postExamen(ex: Examen, onResult: (Examen?) -> Unit){

        val apiBuilder = ServiceBuilder().getClient().create(ExamenService::class.java)

        apiBuilder.postExamen(ex).enqueue(
            object : Callback<Examen> {

                override fun onFailure(call: Call<Examen>, t: Throwable) {
                    Log.e("POST: failure",":c")
                    Log.d("Error:", t.message.toString())
                    onResult(null)
                }

                override fun onResponse( call: Call<Examen>, response: Response<Examen>) {
                    Log.e("POST: success","c:")
                    val addedExam = response.body()
                    onResult(addedExam)
                }
            }
        )
    }

    fun putExamen(id: String, ex: Examen){

        val apiBuilder = ServiceBuilder().getClient().create(ExamenService::class.java)

        apiBuilder.putExamen(id, ex).enqueue(
            object : Callback<Examen> {

                override fun onFailure(call: Call<Examen>, t: Throwable) {
                    Log.e("PUT: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<Examen>, response: Response<Examen>) {
                    Log.e("PUT: success","c:")
                }
            }
        )

    }

    fun deleteExamen(id: String){

        val apiBuilder = ServiceBuilder().getClient().create(ExamenService::class.java)

        apiBuilder.deleteExamen(id).enqueue(
            object : Callback<Examen> {

                override fun onFailure(call: Call<Examen>, t: Throwable) {
                    Log.e("DELETE: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<Examen>, response: Response<Examen>) {
                    Log.e("DELETE: success","c:")
                }
            }
        )

    }

}