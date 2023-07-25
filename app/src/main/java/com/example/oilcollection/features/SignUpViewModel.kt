package com.example.oilcollection.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class SignUpScreenState {

}

class SignUpViewModel(): ViewModel() {

    private val _screenState = MutableLiveData<SignUpScreenState>()
    val screenState = _screenState.value

    fun test(){}

}