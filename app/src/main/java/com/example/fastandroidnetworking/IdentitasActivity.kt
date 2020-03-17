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
import org.json.JSONObject

 class IdentitasActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)
        getdatajadwal()

        back()

        submit.setOnClickListener(){
            var data_namamasjid = nama.text.toString()
            var data_alamatmasjid = alamat.text.toString()

            postkeserver(data_namamasjid,data_alamatmasjid)
            val home = Intent(context,HomeActivity::class.java)
            startActivity(home)
            finish()
        }


    }
     fun back(){
         backfromid.setOnClickListener(){
             val home =Intent(context,HomeActivity::class.java)
             startActivity(home)
             finish()
         }
     }
     fun getdatajadwal(){
         AndroidNetworking.get("https://masjidpro.000webhostapp.com/identitas_json.php")
             .setPriority(Priority.MEDIUM)
             .build()
             .getAsJSONObject(object : JSONObjectRequestListener{
                 override fun onResponse(response: JSONObject?) {
                     Log.e("_kotlinResponse",response.toString())

                     val jsonArray = response!!.getJSONArray("result")
                     for (i in 0 until jsonArray.length()){
                         val jsonObject=jsonArray.getJSONObject(i)
                         nm.setText(jsonObject.optString("nama_masjid"))
                         almt.setText(jsonObject.optString("alamat_masjid"))
                     }
                 }

                 override fun onError(anError: ANError?) {
                     Log.i("_err",anError.toString())
                 }
             })
     }

     fun postkeserver(data1:String, data2:String){
         AndroidNetworking.post("https://masjidpro.000webhostapp.com/proses-identitas.php")
             .addBodyParameter("nama_masjid",data1)
             .addBodyParameter("alamat_masjid",data2)
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
