package com.example.example

import com.google.gson.annotations.SerializedName


data class InfoDTO (

  @SerializedName("title" ) var title : String? = null,
  @SerializedName("value" ) var value : String? = null

)