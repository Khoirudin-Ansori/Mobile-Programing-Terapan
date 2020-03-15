package com.example.praktik_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_3.*

class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val context = this

        BtnIntentUtama2.setOnClickListener(){
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
