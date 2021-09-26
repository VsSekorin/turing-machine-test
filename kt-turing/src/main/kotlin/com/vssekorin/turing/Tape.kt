package com.vssekorin.turing

data class Tape(val left: List<Cell> = emptyList(), val current: Cell = EMPTY, val right: List<Cell> = emptyList()) {

    constructor(word: String): this(current = word.head(), right = word.tail())

    override fun toString(): String {
        return "[$left $current $right]"
    }
}
