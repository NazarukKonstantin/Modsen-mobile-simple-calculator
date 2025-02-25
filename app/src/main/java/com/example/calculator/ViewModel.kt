package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    var state by mutableStateOf(State())
        private set

    fun onAction(action: Action) {
        when (action) {
            is Action.Number -> enterNumber(action.number)
            is Action.Decimal -> enterDecimal()
            is Action.Operation -> enterOperation(action.operation)
            is Action.PerformCalculation -> {
                state = state.copy(isCalculationRequested = true)
                performCalculation()
            }

            is Action.CalculateTemporalResult -> {
                state = state.copy(isCalculationRequested = false)
                performCalculation()
            }

            is Action.Delete -> performDeletion()
            is Action.ChangeSign -> changeSign()
            is Action.FindPercentage -> findPercentage()
        }
    }

    private fun findPercentage() {

    }

    private fun changeSign() {

    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val operationResult = when (state.operation) {
                Operation.Add -> number1 + number2
                Operation.Subtract -> number1 - number2
                Operation.Multiply -> number1 * number2
                Operation.Divide -> number1 / number2
                null -> return
            }
            val resultString = operationResult
                .toString()
                .take(15)
                .removeSuffix(".0")
            state = if (state.isCalculationRequested) {
                state.copy(
                    number1 = resultString,
                    number2 = "",
                    operation = null,
                    result = "",
                    isCalculationRequested = false
                )
            } else {
                state.copy(
                    result = resultString
                )
            }
        }

    }

    private fun enterOperation(operation: Operation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank()
        ) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if (!state.number2.contains(".")
            && state.number2.isNotBlank()
        ) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 30
    }
}