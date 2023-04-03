package com.example.rajaongkir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.rajaongkir.Response.ResponseListProvince
import com.example.rajaongkir.ResponseCity.ResponseListCity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var selectedIdProvinsi:Int = 0
    private var selectedIdCity:Int = 0
    private var selectedEkspedisi:String = ""

    private val listIdProvinsi = ArrayList<Int>()
    private val listProvinsi = ArrayList<String>()

    private val listEksepdisi = arrayListOf("JNE", "POS")

    private val listIdCity = ArrayList<Int>()
    private val listCity = ArrayList<String>()

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

        spinnerEkspedisi.onItemSelectedListener = this@MainActivity
        val adpt = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,listEksepdisi)
        spinnerEkspedisi.adapter = adpt


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if(p0?.selectedItem == spinnerProvinsi.selectedItem){
            selectedIdProvinsi = listIdProvinsi[p2]
            listCitybyProvinsi(selectedIdProvinsi.toString())
            Log.e("Spin","selected id_jp = "+selectedIdProvinsi.toString())
        }else if(p0?.selectedItem == spinnerCity.selectedItem){
            selectedIdCity = listIdCity[p2]
        }else if(p0?.selectedItem == spinnerEkspedisi.selectedItem){
            selectedEkspedisi = listEksepdisi[p2]
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun listCitybyProvinsi(idProvinsi:String){
        ApiRajaOngkir.instance.listCityByProvince(ApiRajaOngkir.apiKey,idProvinsi).enqueue(object :Callback<ResponseListCity>{
            override fun onResponse(
                call: Call<ResponseListCity>,
                response: Response<ResponseListCity>
            ) {
                val res = response.body()!!.rajaongkir?.results
                Log.e("Spin",res.toString())
                listIdCity.clear()
                listCity.clear()
                res?.forEach {
                    it?.cityId?.toInt()?.let { it1 -> listIdCity.add(it1) }
                    listCity.add(it?.cityName.toString())
                }
                //idJp = listIdJP
                spinnerCity.onItemSelectedListener = this@MainActivity
                val adpt = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,listCity)
                spinnerCity.adapter = adpt
            }

            override fun onFailure(call: Call<ResponseListCity>, t: Throwable) {
                Log.e("ERR",t.message.toString())
            }
        })
    }
}