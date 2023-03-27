package com.example.rajaongkir.Response

import com.google.gson.annotations.SerializedName

data class ResponseListProvince(

	@field:SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir? = null
)

data class Rajaongkir(

	@field:SerializedName("query")
	val query: List<Any?>? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class ResultsItem(

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("province_id")
	val provinceId: String? = null
)
