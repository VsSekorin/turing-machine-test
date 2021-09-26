package com.vssekorin.turing

enum class Shift(val delta: Int) {
    L(-1), R(+1), N(0);

    companion object {
        fun of(str: String): Shift {
            return when (str) {
                "+1", "1" -> R
                "-1" -> L
                "0" -> N
                else -> throw IllegalArgumentException("Wrong shift: $str")
            }
        }
    }
}
