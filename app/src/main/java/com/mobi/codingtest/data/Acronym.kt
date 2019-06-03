package com.mobi.codingtest.data

import com.google.gson.annotations.SerializedName

class Acronym {
    @SerializedName("lf")
    var lf: String? = null

    @SerializedName("freq")
    var freq: Int = 0

    @SerializedName("since")
    var since: Int = 0

    @SerializedName("vars")
    var vars: List<Acronym>? = null
}