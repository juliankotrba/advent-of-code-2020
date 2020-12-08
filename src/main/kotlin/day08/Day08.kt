package day08

import parse

fun main() {
    val input = parse("src/main/kotlin/day08/input") { it }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>) = runInstructions(input).second


fun part2(input: List<String>): Int {

    val tobeChanged = input.mapIndexed { i, v -> Pair(i, v) }.filterIndexed { i, v ->
        v.second.startsWith("nop") || v.second.startsWith("jmp")
    }

    tobeChanged.forEach { changePair ->

        val updatedInput = input.toMutableList().apply {
            set(changePair.first,
                    if (changePair.second.startsWith("jmp")) {
                        changePair.second.replace("jmp", "nop")
                    } else {
                        changePair.second.replace("nop", "jmp")
                    }
            )
        }

        val result = runInstructions(updatedInput)
        if (result.first) {
            // updated input terminated
            return result.second
        }
    }

    error("No updated input terminated")
}

fun runInstructions(instructions: List<String>): Pair<Boolean, Int> {
    var acc = 0
    var index = 0

    val visitedIndices = mutableSetOf<Int>().apply { add(0) }
    while (index < instructions.size) {

        val instruction = instructions[index]
        when {
            instruction.startsWith("acc") -> {
                val parameter = instruction.split(" ")[1].toInt()
                acc += parameter
                index += 1
            }
            instruction.startsWith("jmp") -> {
                val parameter = instruction.split(" ")[1].toInt()
                index += parameter
            }
            instruction.startsWith("nop") -> {
                index += 1
            }
            else -> {
                error("$instruction is an invalid instruction")
            }
        }

        if (visitedIndices.contains(index)) {
            return Pair(false, acc)
        } else {
            visitedIndices.add(index)
        }

    }
    return Pair(true, acc)
}