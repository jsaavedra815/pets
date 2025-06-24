package com.pucetec.pets.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class UtilsTest {
    @Test
    fun `should_return_the_sum_between_two numbers`(){
        val result1 = Utils.suma(3,4)
        val result2 = Utils.suma(2,8)
        val result3 = Utils.suma(0,0)
        val result4 = Utils.suma(2,4)
        val result5 = Utils.suma(1,-1)
        val result6 = Utils.suma(8,4)
        val result7 = Utils.suma(0,4)

        assertEquals(7, result1)
        assertEquals(10, result2)
        assertEquals(0, result3)
        assertEquals(6, result4)
        assertEquals(0, result5)
        assertEquals(12, result6)
        assertEquals(4, result7)
    }

    @Test
    fun should_return_the_multiplication_between_two_numbers(){
        val result1 = Utils.multiply(3,4)
        val result2 = Utils.multiply(2,8)

        assertEquals(12, result1)
        assertNotEquals(100, result2)
    }

    @Test
    fun should_return_two_strings_concatenated(){
        val result1 = Utils.concatenate("hola ","como estas")

        assertEquals("hola como estas", result1)
    }

    @Test
    fun should_check_if_two_strings_are_equal(){
        val result1 = Utils.isEqual("hola","hola")
        val result2 = Utils.isEqual("hola","noHola")

        assertTrue(result1)
        assertFalse(result2)
    }

    @Test
    fun should_return_the_correct_response_in_misc(){
        val SUM = "sum"
        val MULTIPLY = "multiply"
        val IS_EQUAL = "is_equal"

        val resultSum = Utils.misc(1,2, SUM)

        val resultMultiply = Utils.misc(1,2, MULTIPLY)

        val resultIsNotEqual = Utils.misc(1,2, IS_EQUAL)

        val resultIsEqual = Utils.misc(2,2, IS_EQUAL)

        val resultDefaultValue = Utils.misc(1,2, "cualquier cosa")

        assertEquals(3, resultSum)
        assertEquals(2, resultMultiply)
        assertEquals(0, resultIsNotEqual)
        assertEquals(1, resultIsEqual)
        assertEquals(0, resultDefaultValue)
    }
}
/***
 * TDD: Test Driven Development
 *
 * LOS TEST UNITARIOS ME PERMITEN REFACTORAR SIN MIEDO A DAÑAR
 *
 * COVERAGE: me permite conocer que porcentaje de mi codigo está probado
 *
 * Para el proyecto final, quiero al menos el 70% de tu codigo testeado
 */