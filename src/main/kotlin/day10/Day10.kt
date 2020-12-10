package day10

import parse

fun main() {
    val input = parse("src/main/kotlin/day10/input") { lines ->
        lines.map { it.toInt() }
    }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<Int>): Int {
    val sorted = input.sorted()

    var oneJoltDiff = 0
    var threeJoltDiff = 0

    for ((i, v) in sorted.withIndex()) {

        if (i + 1 < input.size && v + 1 == sorted[i + 1]) {
            oneJoltDiff++
        }

        if (i + 1 < input.size && v + 3 == sorted[i + 1]) {
            threeJoltDiff++
        }
    }

    return (oneJoltDiff + 1) * (threeJoltDiff + 1)
}

fun part2(input: List<Int>): Int = TODO()
