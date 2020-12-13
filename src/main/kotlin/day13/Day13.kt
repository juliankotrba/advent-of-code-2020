package day13

import parse

data class Input(val time: Long, val buses: List<String>)

fun main() {
    val input = parse("src/main/kotlin/day13/input") { lines ->
        Input(lines[0].toLong(), lines[1].split(","))
    }

    println(part1(input))
    println(chinesReminder(input))
}

fun part1(input: Input): Long {
    return input.buses
            .filter { it != "x" }
            .map { Pair(it, it.toInt() - input.time.rem(it.toInt())) }
            .minBy { it.second }
            ?.let { it.first.toInt() * it.second } ?: 0
}

fun part2(input: Input): Long = chinesReminder(input)

private fun chinesReminder(input: Input): Long {
    val buses = input.buses.mapIndexed { i, b -> Pair(i, b) }.filter { it.second != "x" }
    val n = buses.foldRight(1L) { p, acc -> acc * p.second.toLong() }

    var s = 0L
    buses.forEach {
        val yi = n / it.second.toLong()
        val zi = yi.toBigInteger().modInverse(it.second.toBigInteger())
        val ai = it.second.toLong() - it.first
        s += (yi * zi.toLong() * ai)
    }
    return s.rem(n)
}