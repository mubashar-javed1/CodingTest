package com.mobi.codingtest.networkcall

import com.mobi.codingtest.data.AcronymResponse

import com.mobi.codingtest.networkcall.Status.ERROR
import com.mobi.codingtest.networkcall.Status.LOADING
import com.mobi.codingtest.networkcall.Status.SUCCESS

class ApiResponse private constructor(val status: Status, val data: List<AcronymResponse>?, val error: Throwable?) {
    companion object {
        fun loading(): ApiResponse {
            return ApiResponse(LOADING, null, null)
        }

        fun success(data: List<AcronymResponse>): ApiResponse {
            return ApiResponse(SUCCESS, data, null)
        }

        fun responseError(error: Throwable): ApiResponse {
            return ApiResponse(ERROR, null, error)
        }
    }
}