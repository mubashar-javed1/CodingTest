package com.mobi.codingtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.mobi.codingtest.networkcall.ApiResponse
import com.mobi.codingtest.repository.Repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel internal constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    val responseLiveData = MutableLiveData<ApiResponse>()

    fun getAbbreviationResult(abbreviation: String) {
        disposables.add(repository.getAcronyms(abbreviation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { responseLiveData.setValue(ApiResponse.loading()) }
                .subscribe(
                        { result -> responseLiveData.setValue(ApiResponse.success(result)) },
                        { throwable -> responseLiveData.setValue(ApiResponse.responseError(throwable)) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}