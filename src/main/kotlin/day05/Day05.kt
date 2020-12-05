package day05

import parse

fun main() {
    val input = parse("src/main/kotlin/day05/input") { it }
    println(part1(input))
    println(part2(input))

}

fun part1(input: List<String>): Int = input.map { binaryPartitioning(it) }.max() ?: 0

fun part2(input: List<String>): Int {
    val occupiedSeatSet = input.map { binaryPartitioning(it) }.toSet()

    return occupiedSeatSet.find {
        !occupiedSeatSet.contains(it + 1) && occupiedSeatSet.contains(it + 2)
    }?.let { it + 1 } ?: throw IllegalStateException("Seat must be found")
}

fun binaryPartitioning(inputLine: String): Int =
        Integer.parseInt(inputLine.map { if (it == 'F' || it == 'L') "0" else "1" }.joinToString(separator = ""), 2)