package com.mobi.codingtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.mobi.codingtest.repository.Repository

import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}