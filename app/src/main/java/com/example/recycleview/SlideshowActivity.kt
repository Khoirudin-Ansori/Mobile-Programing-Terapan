package com.example.recycleview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class SlideshowActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)
        val recyclerView = findViewById(R.id.recyclerSlideshow) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val slideshows=ArrayList<Slideshow>()


        AndroidNetworking.get("http://192.168.1.248/Semester_4/Mobile_Programming_Terapan/recycleview/slideshow_json.php")
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
}
