package com.vssekorin.turing

class TuringMachine(
    private val initState: State, private val program: Set<Rule>, private val logged: Boolean = false
): (String) -> Configuration {

    override fun invoke(word: String): Configuration {
        return process(Configuration(initState, 0, Tape(word)))
    }

    private tailrec fun process(conf: Configuration): Configuration {
        val rule: Rule? = program.firstOrNull { it.suit(conf.state, conf.tape.current) }
        if (logged) {
            println("$conf -- $rule")
        }
        return if (rule != null) {
            process(Configuration(rule.newState, conf.position + rule.shift.delta, shift(conf.tape, rule)))
        } else {
            conf
        }
    }

    private fun shift(tape: Tape, rule: Rule): Tape = when (rule.shift) {
        Shift.N -> tape.copy(current = rule.newSymbol)
        Shift.L -> shiftLeft(tape, rule)
        Shift.R -> shiftRight(tape, rule)
    }

    private fun shiftLeft(tape: Tape, rule: Rule): Tape = if (tape.left.isNotEmpty()) {
        tape.copy(current = tape.left.last(), left = tape.left.dropLast(1), right = listOf(rule.newSymbol) + tape.right)
    } else {
        tape.copy(current = EMPTY, right = listOf(rule.newSymbol) + tape.right)
    }

    private fun shiftRight(tape: Tape, rule: Rule): Tape = if (tape.right.isNotEmpty()) {
        tape.copy(current = tape.right.first(), left = tape.left + listOf(rule.newSymbol), right = tape.right.drop(1))
    } else {
        tape.copy(current = EMPTY, left = tape.left + listOf(rule.newSymbol))
    }
}
