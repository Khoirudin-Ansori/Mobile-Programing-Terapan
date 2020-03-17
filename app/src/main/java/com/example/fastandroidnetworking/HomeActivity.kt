package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        jadwal()
        pengumuman()
        marque()
        tagline()
        slideshow()
        identitas()
    }

    fun identitas(){
        identitas.setOnClickListener(){
            val identitas = Intent(context,IdentitasActivity::class.java)
            startActivity(identitas)
            finish()
        }
    }

    fun jadwal(){
        jadwal.setOnClickListener(){
            val jadwal = Intent(context,MainActivity::class.java)
            startActivity(jadwal)
            finish()
        }
    }

    fun pengumuman(){
        pengumuman.setOnClickListener(){
            val pengumuman = Intent(context,PengumumanActivity::class.java)
            startActivity(pengumuman)
            finish()
        }
    }

    fun marque(){
        marque.setOnClickListener(){
            val marque = Intent(context,MarqueActivity::class.java)
            startActivity(marque)
            finish()
        }
    }

    fun tagline(){
        tagline.setOnClickListener(){
            val tagline = Intent(context,TaglineActivity::class.java)
            startActivity(tagline)
            finish()
        }
    }

    fun slideshow(){
        slideshow.setOnClickListener(){
            val slideshow = Intent(context,SlideshowActivity::class.java)
            startActivity(slideshow)
            finish()
        }
    }
}
