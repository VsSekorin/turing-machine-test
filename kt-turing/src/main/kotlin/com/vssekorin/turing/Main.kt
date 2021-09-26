package com.vssekorin.turing

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val variant = "1"
    val tm = TuringMachine("q0", readRules(variant + "p"))

    Files.readAllLines(Paths.get(variant + "d"))
        .filter { it.isNotBlank() && !it.startsWith("--#") }
        .map { it.split("->") }
        .forEach {
            val word = if (it.size > 0) it[0].turingWord() else ""
            val expected = if (it.size > 1) it[1].turingWord() else ""
            val result = tm(word)
            println("$result -- $expected")
        }
}

private fun readRules(filename: String): Set<Rule> {
    return Files.readAllLines(Paths.get(filename))
        .filter { it.isNotBlank() && !it.startsWith("///") }
        .map { Rule.of(it) }
        .toSet()
}
