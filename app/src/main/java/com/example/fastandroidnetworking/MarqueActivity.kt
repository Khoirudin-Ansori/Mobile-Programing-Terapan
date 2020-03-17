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
import kotlinx.android.synthetic.main.activity_main.back
import kotlinx.android.synthetic.main.activity_marque.*
import org.json.JSONObject

class MarqueActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marque)

        getdatamarque()
        back()
        editmarque.setOnClickListener(){
            var data_marque = marque.text.toString()

            postkeserver(data_marque)
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }
    }

    fun getdatamarque(){
        AndroidNetworking.get("https://masjidpro.000webhostapp.com/marque_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        isimarque.setText(jsonObject.optString("isi_marquee"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err",anError.toString())
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

    fun postkeserver(data1:String){
        AndroidNetworking.post("https://masjidpro.000webhostapp.com/proses-marque.php")
            .addBodyParameter("isi_marquee",data1)
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
