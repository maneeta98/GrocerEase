package com.example.grocerease.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grocerease.R
import com.example.grocerease.databinding.ActivityForgetPasswordBinding
import com.example.grocerease.repository.AuthRepoImpl
import com.example.grocerease.utils.LoadingUtils
import com.example.grocerease.viewmodel.AuthViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    lateinit var authViewModel: AuthViewModel
    lateinit var loadingUtils : LoadingUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)

        //initializing auth viewmodel
        var repo = AuthRepoImpl()
        authViewModel = AuthViewModel(repo)

        //initializing loading
        loadingUtils = LoadingUtils(this)
        forgetPasswordBinding.btnForget.setOnClickListener {
            loadingUtils.showDialog()
            var email:String = forgetPasswordBinding.editEmailForget.text.toString()

            authViewModel.forgetPassword(email){
                    success,message->
                if(success){
                    loadingUtils.dismiss()
                    Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    loadingUtils.dismiss()
                    Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()

                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}