package com.healthypocket.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.ContenidoActivity
import com.healthypocket.R
import com.healthypocket.UserViewModel
import com.healthypocket.api.ServiceBuilder
import com.healthypocket.databinding.FragmentIniciarSesionBinding
import com.healthypocket.model.CitaMedica
import com.healthypocket.model.Examen
import com.healthypocket.model.User
import com.healthypocket.model.UserLogin
import com.healthypocket.service.CitasService
import com.healthypocket.service.UserService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.properties.Delegates


class IniciarSesionFragment : Fragment() {

    private var userlist = mutableListOf<User>()
    private var _binding: FragmentIniciarSesionBinding? = null
    private val binding: FragmentIniciarSesionBinding
        get() = _binding!!

    private lateinit var vm: UserViewModel
    //private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentIniciarSesionBinding
            .inflate(inflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iniciarBtn.setOnClickListener {
           val usuario = binding.etUsernameLogin.text.toString()
           val password = binding.etPasswordLogin.text.toString()

            if(usuario.isEmpty()){
                binding.etUsernameLogin.error = "Error Usuario"
                binding.etUsernameLogin.requestFocus()
                return@setOnClickListener
            }
            else if(password.isEmpty()){
                binding.etPasswordLogin.error = "Error Contraseña"
                binding.etPasswordLogin.requestFocus()
                return@setOnClickListener
            }else {
                signin(usuario, password)
            }
        }


        binding.recuperarCuentaBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_recuperar)
        }

        binding.crearCuentaBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_registrarse)
        }
    }
    private fun signin(username: String?, password: String?){
        val serviceBuilder = ServiceBuilder()
        val retIn = serviceBuilder.getClient2().create(UserService::class.java)
        val loginInfo = UserLogin(username,password)
        retIn.loginUser(loginInfo).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.code() == 200){
                        Log.e("LOGINPASO:", response.code().toString())
                        Toast.makeText(requireContext(), "Login success!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, ContenidoActivity::class.java)
                        startActivity(intent)
                        activity?.finish()

                    } else {
                        Log.e("NOPASOLOGIN:", response.code().toString())
                        Toast.makeText(requireContext(), "Login failed!", Toast.LENGTH_SHORT).show()
                    }
                }


            })

    }


    }
