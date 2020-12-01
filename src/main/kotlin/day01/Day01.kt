package day1

import listOfInt
import parse

fun main() {
    val input = parse("src/main/kotlin/day01/input", ::listOfInt)
    part1(input)
    part2(input)

}

fun part1(input: List<Int>) {
    input.forEach { first ->
        input.forEach { second ->
            if (first + second == 2020) {
                println(first * second)
                return@part1
            }
        }
    }
}

fun part2(input: List<Int>) {
    input.forEach { first ->
        input.forEach { second ->
            input.forEach { third ->
                if (first + second + third == 2020) {
                    println(first * second*third)
                    return@part2
                }
            }
        }
    }
}


