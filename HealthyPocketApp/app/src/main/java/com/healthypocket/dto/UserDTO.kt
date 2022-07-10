package com.healthypocket.dto

import android.util.Log
import com.healthypocket.api.ServiceBuilder
import com.healthypocket.model.User
import com.healthypocket.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDTO {

    private var userData = mutableListOf<User>()

    fun getUsers(cb: (R:List<User>)->Unit){
        val apiBuilder = ServiceBuilder().getClient().create(UserService::class.java)
        apiBuilder.getAllUsers().enqueue(
            object : Callback<List<User>> {

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
                    cb(response.body()!!)
                    userData.addAll(response.body()!!)
                    Log.d("jej",userData.toString())
                    Log.e("GET: success","c:")
                }
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.e("GET: failure",":c")
                    Log.d("Error:", t.message.toString())
                }
            }
        )
    }

    fun postUser(user: User, onResult: (User?) -> Unit){

        val apiBuilder = ServiceBuilder().getClient().create(UserService::class.java)

        apiBuilder.postUser(user).enqueue(
            object : Callback<User> {

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("GET: failure",":c")
                    Log.d("Error:", t.message.toString())
                    onResult(null)
                }

                override fun onResponse( call: Call<User>, response: Response<User>) {
                    Log.e("GET: success","c:")
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun putUser(id: String, user: User){

        val apiBuilder = ServiceBuilder().getClient().create(UserService::class.java)

        apiBuilder.putUser(id, user).enqueue(
            object : Callback<User> {

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("PUT: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<User>, response: Response<User>) {
                    Log.e("PUT: success","c:")
                }
            }
        )

    }

    fun deleteUser(id: String){

        val apiBuilder = ServiceBuilder().getClient().create(UserService::class.java)

        apiBuilder.deleteUser(id).enqueue(
            object : Callback<User> {

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("DELETE: failure",":c")
                    Log.d("Error:", t.message.toString())
                }

                override fun onResponse( call: Call<User>, response: Response<User>) {
                    Log.e("DELETE: success","c:")
                }
            }
        )

    }


    suspend fun getSingleUser(id:String): User{
        val api = ServiceBuilder().getClient().create(UserService::class.java)
        val response = api.getSingleUser(id)
        if (response.isSuccessful){
            return response.body()?: User(
                "",
                "",
                "",
                "",
                "",
                ""
            )
        }

        return User(
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }

}