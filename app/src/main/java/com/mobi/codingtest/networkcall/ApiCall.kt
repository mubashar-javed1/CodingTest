package com.mobi.codingtest.networkcall

import com.mobi.codingtest.data.AcronymResponse
import com.mobi.codingtest.networkcall.ApiConfig.GET_ACRONYM
import com.mobi.codingtest.networkcall.ApiConst.SF

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    @GET(GET_ACRONYM)
    fun getAcronym(@Query(SF) sf: String): Observable<List<AcronymResponse>>
}