package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdariServer()
        back()
        editjadwal.setOnClickListener(){
            var data_subuh = subuh.text.toString()
            var data_duhur = duhur.text.toString()
            var data_asar = asar.text.toString()
            var data_magrib = magrib.text.toString()
            var data_isa = isa.text.toString()
            var data_duha = duha.text.toString()

            postkeserver(data_subuh,data_duhur,data_asar,data_magrib,data_isa,data_duha)
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }


    }

    fun getdariServer(){
        AndroidNetworking.get("https://masjidpro.000webhostapp.com/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle",jsonObject.optString("shubuh"))
                        txt1.setText("Shubuh  " + jsonObject.optString("shubuh"))
                        txt2.setText("Dhuhur  " + jsonObject.optString("dhuhur"))
                        txt3.setText("Ashar   " + jsonObject.optString("ashar"))
                        txt4.setText("Maghrib " + jsonObject.optString("maghrib"))
                        txt5.setText("Isya    " + jsonObject.optString("isha"))
                        txt6.setText("Dhuha   " + jsonObject.optString("dhuha"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun back(){
        back.setOnClickListener(){
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }
    }

    fun postkeserver(data1:String, data2:String, data3:String, data4:String, data5:String, data6:String){
        AndroidNetworking.post("https://masjidpro.000webhostapp.com/proses-jadwal.php")
            .addBodyParameter("shubuh",data1)
            .addBodyParameter("dhuhur",data2)
            .addBodyParameter("ashar",data3)
            .addBodyParameter("maghrib",data4)
            .addBodyParameter("isha",data5)
            .addBodyParameter("dhuha",data6)

            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                }
                override fun onError(anError: ANError?) {
                }
            })
    }

}
