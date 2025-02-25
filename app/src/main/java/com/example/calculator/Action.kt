package com.example.calculator

sealed class Action {
    data class Number(val number: Int) : Action()
    data class Operation(val operation: com.example.calculator.Operation) : Action()
    object Delete : Action()
    object Decimal : Action()
    object CalculateTemporalResult : Action()
    object PerformCalculation : Action()
    object ChangeSign : Action()
    object FindPercentage : Action()
}