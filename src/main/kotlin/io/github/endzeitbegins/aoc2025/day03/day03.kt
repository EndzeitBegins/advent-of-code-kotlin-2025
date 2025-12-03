package io.github.endzeitbegins.aoc2025.day03

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput

private fun part1(input: String): Int =
    input
        .lineSequence()
        .sumOf { bank ->
            val joltages = bank.indices.groupBy { index -> bank[index].digitToInt() }

            val indexA = (9 downTo 0).firstNotNullOf { joltage ->
                joltages[joltage]?.firstOrNull { index -> index < bank.lastIndex }
            }
            val indexB = (9 downTo 0).firstNotNullOf { joltage ->
                joltages[joltage]?.firstOrNull { index -> index > indexA }
            }

            "${bank[indexA]}${bank[indexB]}".toInt()
        }

private fun part2(input: String): Int {
    return input.length
}

fun main() {
    val testInput = readInput("day03/test-input.txt")
    val input = readInput("day03/input.txt")

    // part 1
    checkSolution(part1(testInput), 357)
    println(part1(input))

    // part 2
    // checkSolution(part2(testInput), 1)
    // println(part2(input))
}