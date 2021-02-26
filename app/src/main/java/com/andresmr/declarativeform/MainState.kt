package com.andresmr.declarativeform

data class MainState(
    val title: String,
    val messages: List<String>,
    val allSelected: Boolean
)