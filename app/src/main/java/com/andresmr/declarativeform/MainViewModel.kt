package com.andresmr.declarativeform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val title = "Let's Rock"
    private val repository = listOf(
        "Extremoduro",
        "Fito y Fitipaldis",
        "Marea",
        "Reincidentes",
        "Sinkope",
        "Derby Motoreta's Burrito Cachimba",
        "Califato 3/4",
        "Extremoduro",
        "Fito y Fitipaldis",
        "Marea",
        "Reincidentes",
        "Sinkope",
        "Derby Motoreta's Burrito Cachimba",
        "Califato 3/4"
    )

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState>
        get() = _uiState

    init {
        _uiState.value = MainState(
            title,
            messages = repository,
            allSelected = false
        )
    }

    fun onCheckChange(selected: Boolean) {
        _uiState.value = MainState(
            title,
            messages = repository,
            allSelected = selected
        )
    }
}