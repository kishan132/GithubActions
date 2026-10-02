package me.kishankumar.githubactions

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorLogicTest {

    private lateinit var calculator: CalculatorLogic

    @Before
    fun setUp() {
        calculator = CalculatorLogic()
    }

    @Test
    fun initialDisplay_isZero() {
        assertEquals("0", calculator.display)
    }

    @Test
    fun digitInput_updatesDisplay() {
        calculator.onDigitInput("5")
        assertEquals("5", calculator.display)

        calculator.onDigitInput("3")
        assertEquals("53", calculator.display)
    }

    @Test
    fun addition_calculatesCorrectResult() {
        calculator.onDigitInput("1")
        calculator.onDigitInput("2")
        calculator.onOperationInput("+")
        calculator.onDigitInput("8")
        calculator.onEquals()

        assertEquals("20", calculator.display)
    }

    @Test
    fun divisionByZero_displaysError() {
        calculator.onDigitInput("9")
        calculator.onOperationInput("/")
        calculator.onDigitInput("0")
        calculator.onEquals()

        assertEquals("Error", calculator.display)
    }

    @Test
    fun clear_resetsDisplayToZero() {
        calculator.onDigitInput("7")
        calculator.onClear()

        assertEquals("0", calculator.display)
    }
}
