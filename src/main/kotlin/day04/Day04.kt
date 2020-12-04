package day04

import parse

fun main() {
    val input = parse("src/main/kotlin/day04/input") {
        day4parser(it)
    }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<Map<String, String>>) = input.count { isValidPart1(it) }
fun part2(input: List<Map<String, String>>) = input.count { isValidPart2(it) }

fun isValidPart1(pw: Map<String, String>) = pw.size == 8 || (pw.size == 7 && !pw.containsKey("cid"))

fun isValidPart2(pw: Map<String, String>): Boolean =
        pw.containsKey("byr") && numberValid(pw["byr"] ?: "", 1920, 2002) &&
                pw.containsKey("iyr") && numberValid(pw["iyr"] ?: "", 2010, 2020) &&
                pw.containsKey("eyr") && numberValid(pw["eyr"] ?: "", 2020, 2030) &&
                pw.containsKey("hgt") && hgtValid(pw["hgt"] ?: "") &&
                pw.containsKey("hcl") && hairColorValid(pw["hcl"] ?: "") &&
                pw.containsKey("ecl") && eyeColorValid(pw["ecl"] ?: "") &&
                pw.containsKey("pid") && idValid(pw["pid"] ?: "")

fun numberValid(numberString: String, min: Int, max: Int) = numberString.toInt() in min..max

fun hairColorValid(s: String) = s.startsWith("#") && s.drop(1).any { it.isDigit() || it.isLowerCase() }

fun eyeColorValid(s: String) =
        s == "amb" || s == "blu" || s == "brn" || s == "gry" || s == "grn" || s == "hzl" || s == "oth"

fun hgtValid(s: String) = when {
    s.endsWith("cm") -> {
        s.dropLast(2).toInt() in 150..193
    }
    s.endsWith("in") -> {
        s.dropLast(2).toInt() in 59..76
    }
    else -> false
}

fun idValid(s: String) = s.any { it.isDigit() } && s.length == 9

fun day4parser(lines: List<String>): List<Map<String, String>> {
    val pws = mutableListOf<Map<String, String>>()

    var currPws = mutableMapOf<String, String>()
    lines.forEach {
        if (it.isBlank()) {
            pws.add(currPws)
            currPws = mutableMapOf()
        } else {
            it.lines().forEach { pwLine ->
                pwLine.split(" ").forEach { kv ->
                    currPws[kv.split(":")[0]] = kv.split(":")[1]
                }
            }
        }
    }

    return pws.apply { add(currPws) }
}