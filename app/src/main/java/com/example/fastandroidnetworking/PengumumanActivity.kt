package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_marque.*
import kotlinx.android.synthetic.main.activity_marque.back
import kotlinx.android.synthetic.main.activity_pengumuman.*
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONObject

class PengumumanActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        back()
        getdatapengumuman()
        editpengumuman.setOnClickListener(){
            var data_judulpengumuman = editjdlpeng.text.toString()
            var data_isipengumuman = editisipeng.text.toString()

            postkeserver(data_judulpengumuman,data_isipengumuman)
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }


    }

    fun back(){
        back.setOnClickListener(){
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }
    }

    fun getdatapengumuman(){
        AndroidNetworking.get("https://masjidpro.000webhostapp.com/pengumuman_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        judulpeng.setText(jsonObject.optString("judul_pengumuman"))
                        isipeng.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err",anError.toString())
                }
            })
    }

    fun postkeserver(data1:String,data2:String){
        AndroidNetworking.post("https://masjidpro.000webhostapp.com/proses-pengumuman.php")
            .addBodyParameter("judul_pengumuman",data1)
            .addBodyParameter("isi_pengumuman",data2)
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
