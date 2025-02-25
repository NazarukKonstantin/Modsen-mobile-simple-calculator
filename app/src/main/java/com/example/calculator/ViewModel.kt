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
        if (state.operation == null &&
            state.number1.displayedValue.last() != '.' &&
            !state.number1.displayedValue.contains('%') &&
            state.number1.displayedValue.isNotBlank()
        ) {
            state = state.copy(
                number1 = state.number1.addPercent()
            )
            return
        }
        if (state.number2.displayedValue.last() != '.' &&
            !state.number2.displayedValue.contains('%') &&
            state.number2.displayedValue.isNotBlank()
        ) {
            state = state.copy(
                number2 = state.number2.addPercent()
            )
        }
    }

    private fun changeSign() {
        if (state.operation == null &&
            state.number1.displayedValue.isNotBlank()
        ) {
            state = state.copy(
                number1 = state.number1.changeSign()
            )
            return
        }
        if (state.number2.displayedValue.isNotBlank()
        ) {
            state = state.copy(
                number2 = state.number2.changeSign()
            )
        }
    }

    private fun performDeletion() {
        when {
            state.number2.displayedValue.isNotBlank() -> {
                state = state.copy(
                    number2 = state.number2.deleteLastCharacter()
                )
            }

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.displayedValue.isNotBlank() ->
                state = state.copy(
                    number1 = state.number1.deleteLastCharacter()
                )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.realValue.toDoubleOrNull()
        val number2 = state.number2.realValue.toDoubleOrNull()
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
                    number1 = Number(resultString, resultString),
                    number2 = Number(),
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
        if (state.number1.displayedValue.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null &&
            !state.number1.displayedValue.contains(".") &&
            !state.number1.displayedValue.contains('%') &&
            state.number1.displayedValue.isNotBlank()
        ) {

            state = state.copy(
                number1 = state.number1.addDecimal()
            )
            return
        }
        if (!state.number2.displayedValue.contains(".") &&
            !state.number2.displayedValue.contains('%') &&
            state.number2.displayedValue.isNotBlank()
        ) {
            state = state.copy(
                number2 = state.number2.addDecimal()
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.displayedValue.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = Number(
                    state.number1.displayedValue + number,
                    state.number1.realValue + number
                )
            )
            return
        }
        if (state.number2.displayedValue.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = Number(
                state.number2.displayedValue + number,
                state.number2.realValue + number
            )
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 30
    }
}