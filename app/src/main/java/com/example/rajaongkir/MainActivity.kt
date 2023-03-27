package com.example.rajaongkir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.rajaongkir.Response.ResponseListProvince
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var selectedIdProvinsi:Int = 0
    private val listIdProvinsi = ArrayList<Int>()
    private val listProvinsi = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //----- list provinsi ----------
        ApiRajaOngkir.instance.listprovince(ApiRajaOngkir.apiKey).enqueue(object :Callback<ResponseListProvince>{
            override fun onResponse(
                call: Call<ResponseListProvince>,
                response: Response<ResponseListProvince>
            ) {
                val res = response.body()!!.rajaongkir?.results
                Log.e("Spin",res.toString())
                res?.forEach {
                    it?.provinceId?.toInt()?.let { it1 -> listIdProvinsi.add(it1) }
                    listProvinsi.add(it?.province.toString())
                }
                //idJp = listIdJP
                spinnerProvinsi.onItemSelectedListener = this@MainActivity
                val adpt = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,listProvinsi)
                spinnerProvinsi.adapter = adpt
            }

            override fun onFailure(call: Call<ResponseListProvince>, t: Throwable) {
                Log.e("ERR",t.message.toString())
            }
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if(p0?.selectedItem == spinnerProvinsi.selectedItem){
            selectedIdProvinsi = listIdProvinsi[p2]
            Log.e("Spin","selected id_jp = "+selectedIdProvinsi.toString())
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun listCitybyProvinsi(idProvinsi:String){
        
    }
}