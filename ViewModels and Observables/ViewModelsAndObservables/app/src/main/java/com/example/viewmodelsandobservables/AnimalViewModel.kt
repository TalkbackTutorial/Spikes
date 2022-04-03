package com.example.viewmodelsandobservables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalViewModel : ViewModel() {

    val animalName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun duplicateName() {
        this.animalName.value += this.animalName.value
    }

}