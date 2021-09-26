package com.vssekorin.turing

typealias State = String
typealias Cell = String

const val EMPTY: Cell = "∧"

fun String.head(): Cell = this.first().toString()

fun String.tail(): List<Cell> = this.takeLast(this.length - 1).toList().map { it.toString() }

fun String.turingWord(): String = this.trim().replace(" ", EMPTY)
