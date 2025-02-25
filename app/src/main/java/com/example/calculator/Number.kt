package com.example.calculator

open class Number(
    val displayedValue: String = "",
    val realValue: String = ""
) {
    override fun toString(): String {
        return this.displayedValue
    }

    fun appendDigit(newNum: Int): Number {
        return Number(
            (this.displayedValue + newNum),
            (this.realValue + newNum)
        )
    }

    fun deleteLastCharacter(): Number {
        val currentDisplayedValue: String = this.displayedValue
        val currentRealValue: String = this.realValue
        val newDisplayedValue: String = currentDisplayedValue.dropLast(1)
        val newRealValue: String = (if (currentDisplayedValue.last() == '%') {
            (currentRealValue.toDouble() * 100).toString()
        } else {
            currentRealValue.dropLast(1)
        })
        return Number(newDisplayedValue, newRealValue)
    }

    fun addDecimal(): Number {
        return Number(
            "${this.displayedValue}.",
            "${this.realValue}."
        )
    }

    fun addPercent(): Number {
        return Number(
            "${this.displayedValue}%",
            "${this.realValue.toDouble() * 0.01}"
        )
    }

    fun changeSign(): Number {
        return Number(
            changeValueSign(this.displayedValue),
            changeValueSign(this.realValue)
        )
    }

    private fun changeValueSign(value: String): String {
        return (if (value.first() == '-') {
            value.drop(1)
        } else {
            "-$value"
        })
    }
}