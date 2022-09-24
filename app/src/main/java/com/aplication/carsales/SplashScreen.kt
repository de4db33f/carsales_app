package com.aplication.carsales

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.aplication.carsales.main_module.view.MainActivity

@SuppressLint("CustomSplashScreen")
//FIXME: Se agregó un splash screen custom para las API anteriores a 31, de todas formas en Theme se setearon las flags siguiendo las recomendaciones de la documentación
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}