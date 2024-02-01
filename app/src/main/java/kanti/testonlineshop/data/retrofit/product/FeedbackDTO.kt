package com.example.example

import com.google.gson.annotations.SerializedName


data class FeedbackDTO (

  @SerializedName("count"  ) var count  : Int?    = null,
  @SerializedName("rating" ) var rating : Double? = null

)