package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_marque.*
import kotlinx.android.synthetic.main.activity_marque.back
import kotlinx.android.synthetic.main.activity_marque.isimarque
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONObject

class TaglineActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagline)

        getdatatagline()
        back()
        edittagline.setOnClickListener(){
            var data_isitagline = editisitagline.text.toString()

            postkeserver(data_isitagline)
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }
    }

    fun postkeserver(data1:String){
        AndroidNetworking.post("https://masjidpro.000webhostapp.com/proses-tagline.php")
            .addBodyParameter("isi_tagline",data1)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                }
                override fun onError(anError: ANError?) {
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

    fun getdatatagline(){
        AndroidNetworking.get("https://masjidpro.000webhostapp.com/tagline_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        isitag.setText(jsonObject.optString("isi_tagline"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err",anError.toString())
                }
            })
    }
}
