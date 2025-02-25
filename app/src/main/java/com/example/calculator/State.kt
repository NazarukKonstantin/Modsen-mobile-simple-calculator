package com.example.calculator

data class State(
    val number1: Number = Number(),
    val number2: Number = Number(),
    val operation: Operation? = null,
    val result: String = "",
    val isCalculationRequested: Boolean = false,
)