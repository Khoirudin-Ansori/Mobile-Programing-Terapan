package com.example.uts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_penduduk.*
import org.json.JSONArray

class PendudukActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penduduk)

        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@PendudukActivity, LoginActivity::class.java))
            finish()
        }

        insert.setOnClickListener() {
            var nama = nama_penduduk.text.toString()
            var ttl = ttl_penduduk.text.toString()
            var hp = hp_penduduk.text.toString()
            var alamat = alamat_penduduk.text.toString()
            postkeserver(nama, ttl, hp, alamat)

            val intent = Intent(this@PendudukActivity, MainActivity::class.java)
            startActivity(intent)
        }


    }
    fun postkeserver(data: String, data2: String, data3: String, data4:String) {
        AndroidNetworking.post("https://mahasiswavokasi.000webhostapp.com/proses-penduduk.php")
            .addBodyParameter("nama_penduduk", data)
            .addBodyParameter("ttl_penduduk", data2)
            .addBodyParameter("hp_penduduk", data3)
            .addBodyParameter("alamat_penduduk", data4)
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
