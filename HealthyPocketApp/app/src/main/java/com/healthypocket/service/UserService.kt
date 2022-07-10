package com.healthypocket.service

import com.healthypocket.model.Medicamento
import com.healthypocket.model.User
import com.healthypocket.model.UserLogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @GET("users/")
    fun getAllUsers() : Call<List<User>>

    @PUT("users/{id}/")
    fun putUser(@Path("id") string: String, @Body user: User): Call<User>

    @POST("users/register/")
    fun postUser(@Body user: User) : Call<User>

    @DELETE("users/{id}/")
    fun deleteUser(@Path("id") string: String): Call<User>

    @POST("users/login/")
    fun loginUser(@Body user: UserLogin) : Call<ResponseBody>

    @GET("users/{id}/")
    suspend fun getSingleUser(@Path("id") id: String) : Response<User>
}