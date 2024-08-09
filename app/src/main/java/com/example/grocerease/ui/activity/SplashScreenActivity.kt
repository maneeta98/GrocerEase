package com.example.grocerease.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.grocerease.R
import com.example.grocerease.databinding.ActivitySplashScreenBinding
import com.example.grocerease.repository.AuthRepoImpl
import com.example.grocerease.viewmodel.AuthViewModel


class SplashScreenActivity : AppCompatActivity() {

    lateinit var splashScreenActivityBinding: ActivitySplashScreenBinding
    lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreenActivityBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenActivityBinding.root)

        //initializing auth viewmodel
        var repo = AuthRepoImpl()
        authViewModel = AuthViewModel(repo)


        var currentUser = authViewModel.getCurrentUser()

        Handler(Looper.getMainLooper()).postDelayed({
            if(currentUser == null){
                var intent = Intent(this@SplashScreenActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                var intent = Intent(this@SplashScreenActivity,NavigationActivity::class.java)
                startActivity(intent)
                finish()
            }

        },3000)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}