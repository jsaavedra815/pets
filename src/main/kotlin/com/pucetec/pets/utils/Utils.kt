package com.pucetec.pets.utils

class Utils {
    companion object {
        fun suma(a: Int, b: Int): Int {
            return a + b
        }

        fun multiply(a: Long, b: Long): Long {
            return a * b
        }

        fun concatenate(a: String, b: String): String {
            return a + b
        }

        fun isEqual(a: String, b: String): Boolean {
            return a == b
        }

        fun misc(a: Int, b: Int, operation: String): Int {
            when(operation) {
                "sum" -> return a + b
                "multiply" -> return a * b
                "is_equal" -> return if(a == b) 1 else 0
            }
            return 0
        }
    }
}