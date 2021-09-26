package com.vssekorin.turing

data class Rule(val state: State, val symbol: Cell, val newState: State, val newSymbol: Cell, val shift: Shift) {

    fun suit(st: String, sm: String): Boolean {
        return state == st && symbol == sm
    }

    override fun toString(): String {
        return "[$state, $symbol -> $newState, $newSymbol, ${shift.delta}]"
    }

    companion object {
        fun of(str: String): Rule {
            return str.trim().split("\\s+".toRegex()).let {
                Rule(it[0], it[1], it[3], it[4], Shift.of(it[5]))
            }
        }
    }
}
