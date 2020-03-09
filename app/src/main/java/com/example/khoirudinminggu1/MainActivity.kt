package com.example.khoirudinminggu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//
//        var jam = 12
//        if (jam<= 12)
//        {
//            Log.i("Hasil","Selamat Pagi")
//            tampil.text="nilai Anda A"
//        }
//        else
//        {
//            Log.i("Hasil", "Selamat Petang")
//            tampil.text="nilai Anda AB"
//
//        }

        /*var ip = 80

        if(ip >= 90 && ip <100)
        {
            Log.i("Nilai Anda", "A")
            tampil.text="nilai Anda A"

        }
        else if(ip >= 80 && ip <90)
        {
            Log.i("Nilai Anda", "AB")
            tampil.text="nilai Anda AB"

        }
        else if(ip >= 70 && ip < 80)
        {
            Log.i("Nilai Anda", "B")
            tampil.text="nilai Anda B"
        }
        else if(ip >= 60 && ip < 70)
        {
            Log.i("Nilai Anda", "BC")
            tampil.text="nilai Anda BC"
        }
        else if(ip < 60)
        {
            Log.i("Nilai Anda", "C")
            tampil.text="nilai Anda C"

        }*/

//        for (x in 0..10)
//            Log.i("Hasil","$x")

        /*var i =1

        while (i <= 5)
        {
            Log.i("Hasil", "$i")
            ++i
        }*/

//        var num =2
//        var i = 1
//        do{
//            Log.i("Hasil","2 * $i = " + num *i)
//            i++
//        }while(i<11)

//        tampil()
//        var a=7
//        var b= 5
//        penjumlahan(a,b)
        var a=5
        var b=6
        var c=10
        hitung(a,b,c)



    }

//    fun tampil()
//    {
//        Log.i("Hasil","SUKSES!!!")
//    }

//    fun penjumlahan(a:Int, b:Int)
//    {
//        var c :Int
//        c= a + b
//        Log.i("Hasil","$c")
//    }
    fun hitung(a:Int, b:Int,c:Int)
{
    var d = (a*b-10)/c
    Log.i("Hasil","$d")
}



}
