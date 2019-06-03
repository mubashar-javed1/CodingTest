package com.mobi.codingtest.repository

import com.mobi.codingtest.data.AcronymResponse
import com.mobi.codingtest.networkcall.ApiCall

import io.reactivex.Observable

class Repository(private val apiCall: ApiCall) {
    fun getAcronyms(abbreviation: String): Observable<List<AcronymResponse>> {
        return apiCall.getAcronym(abbreviation)
    }
}