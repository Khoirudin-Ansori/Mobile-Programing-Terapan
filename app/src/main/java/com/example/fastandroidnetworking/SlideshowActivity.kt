package com.example.fastandroidnetworking

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
import kotlinx.android.synthetic.main.activity_marque.*
import kotlinx.android.synthetic.main.activity_marque.back
import kotlinx.android.synthetic.main.activity_slideshow.*
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONObject

class SlideshowActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        back()
        getdataslideshow()
        editslideshow.setOnClickListener(){
            var data_judulslideshow = editjudulslideshow.text.toString()
            var data_urlslideshow = editurlslideshow.text.toString()

            postkeserver(data_judulslideshow,data_urlslideshow)
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

    fun getdataslideshow(){
        val recyclerView = findViewById(R.id.recyclerSlideshow) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val slideshows=ArrayList<Slideshow>()
        AndroidNetworking.get("https://masjidpro.000webhostapp.com/slideshow_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("judul_slideshow").toString()
                        var isi2=jsonObject.optString("url_slideshow").toString()

                        slideshows.add(Slideshow("$isi1", "$isi2"))


                    }

                    val adapter=AdapterSlideshow(slideshows)
                    recyclerView.adapter=adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver(data1:String,data2:String){
        AndroidNetworking.post("https://masjidpro.000webhostapp.com/proses-slideshow.php")
            .addBodyParameter("judul_slideshow",data1)
            .addBodyParameter("url_slideshow",data2)
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
