package com.alisdnn.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenericResponse<T>(

        @SerializedName("type")
        @Expose var type: String,

        @SerializedName("value")
        @Expose var t: T

)