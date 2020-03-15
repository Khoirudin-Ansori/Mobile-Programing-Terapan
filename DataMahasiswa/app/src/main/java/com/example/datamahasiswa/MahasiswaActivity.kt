package com.example.datamahasiswa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_mahasiswa.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button
import org.json.JSONArray

class MahasiswaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)
        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@MahasiswaActivity, LoginActivity::class.java))
            finish()
        }

        insert.setOnClickListener() {
            var nama = nama_mahasiswa.text.toString()
            var nim = nim_mahasiswa.text.toString()
            var alamat = alamat_mahasiswa.text.toString()
            postkeserver(nama, nim, alamat)

            val intent = Intent(this@MahasiswaActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun postkeserver(data: String, data2: String, data3: String) {
        AndroidNetworking.post("https://mahasiswavokasi.000webhostapp.com/proses_mahasiswa.php")
            .addBodyParameter("nama_mahasiswa", data)
            .addBodyParameter("nim_mahasiswa", data2)
            .addBodyParameter("alamat_mahasiswa", data3)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                }
                override fun onError(anError: ANError?) {
                }
            })
    }
}
