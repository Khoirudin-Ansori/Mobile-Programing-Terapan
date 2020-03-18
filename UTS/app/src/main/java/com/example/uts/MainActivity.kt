package com.example.uts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ketmbh.setOnClickListener() {
            val tambah = Intent(this@MainActivity, PendudukActivity::class.java)
            startActivity(tambah)
            finish()
        }
        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }


        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val penduduk = ArrayList<Penduduk>()


        AndroidNetworking.get("https://mahasiswavokasi.000webhostapp.com/penduduk-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("id_data"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1 = jsonObject.optString("nama_penduduk").toString()
                        var isi2 = jsonObject.optString("ttl_penduduk").toString()
                        var isi3 = jsonObject.optString("hp_penduduk").toString()
                        var isi4 = jsonObject.optString("alamat_penduduk").toString()


                        penduduk.add(Penduduk("$isi1", "$isi2", "$isi3", "$isi4"))


                    }

                    val adapter = CustomAdapter(penduduk)
                    recyclerView.adapter = adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

    }



}
