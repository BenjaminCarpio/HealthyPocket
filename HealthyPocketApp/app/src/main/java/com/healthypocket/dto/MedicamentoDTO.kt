package com.healthypocket.dto

import android.util.Log
import com.healthypocket.api.ServiceBuilder
import com.healthypocket.model.Medicamento
import com.healthypocket.service.MedicamentoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicamentoDTO {

    private var medicamentoData = mutableListOf<Medicamento>()

    fun getMeds(cb: (R:List<Medicamento>)->Unit){
        val apiBuilder = ServiceBuilder().getClient().create(MedicamentoService::class.java)
        apiBuilder.getMedicamentos().enqueue(
            object : Callback<List<Medicamento>> {

            override fun onResponse(call: Call<List<Medicamento>>, response: Response<List<Medicamento>>){
                cb(response.body()!!)
                medicamentoData.addAll(response.body()!!)
                Log.d("jej",medicamentoData.toString())
                Log.e("GET: success","c:")
            }
            override fun onFailure(call: Call<List<Medicamento>>, t: Throwable) {
                Log.e("GET: failure",":c")
                Log.d("Error:", t.message.toString())
            }
        }
        )
    }

    fun postMed(medicamento: Medicamento, onResult: (Medicamento?) -> Unit){

        val apiBuilder = ServiceBuilder().getClient().create(MedicamentoService::class.java)

        apiBuilder.postMedicamentos(medicamento).enqueue(
            object : Callback<Medicamento> {

                override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                    Log.e("GET: failure",":c")
                    Log.d("Error:", t.message.toString())
                    onResult(null)
                }

                override fun onResponse( call: Call<Medicamento>, response: Response<Medicamento>) {
                    Log.e("GET: success","c:")
                    val addedMed = response.body()
                    onResult(addedMed)
                }
            }
        )
    }

    fun putMeds(id: String, medicamento: Medicamento){

        val apiBuilder = ServiceBuilder().getClient().create(MedicamentoService::class.java)

        apiBuilder.putMedicamentos(id, medicamento).enqueue(
            object : Callback<Medicamento> {

                override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                    Log.e("PUT: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<Medicamento>, response: Response<Medicamento>) {
                    Log.e("PUT: success","c:")
                }
            }
        )

    }

    fun deleteMeds(id: String){

        val apiBuilder = ServiceBuilder().getClient().create(MedicamentoService::class.java)

        apiBuilder.deleteMedicamentos(id).enqueue(
            object : Callback<Medicamento> {

                override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                    Log.e("DELETE: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<Medicamento>, response: Response<Medicamento>) {
                    Log.e("DELETE: success","c:")
                }
            }
        )

    }



}