package com.vssekorin.turing

data class Configuration(val state: State, val position: Int, val tape: Tape) {

    override fun toString(): String {
        return "($state, $position, $tape)"
    }
}
