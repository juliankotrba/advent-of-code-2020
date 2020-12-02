package day02

import parse

fun main() {
    val input = parse("src/main/kotlin/day02/input") {
        it.map {
            val split = it.split(" ")
            val minMax = split[0].split("-")
            Policy(minMax[0].toInt(), minMax[1].toInt(), split[1].first(), split[2])
        }
    }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<Policy>): Int = input.filter(::isValidPart1).count()

fun isValidPart1(policy: Policy): Boolean =
        policy.pw.count {
            it == policy.char
        }.let {
            it >= policy.min && it <= policy.max
        }

fun part2(input: List<Policy>): Int = input.filter(::isValidPart2).count()

fun isValidPart2(policy: Policy): Boolean =
        (policy.pw[policy.min - 1] == policy.char) xor (policy.pw[policy.max - 1] == policy.char)


data class Policy(val min: Int, val max: Int, val char: Char, val pw: String)