package io.github.endzeitbegins.aoc2025.day05

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput

private fun part1(input: String): Int {
    val (rangeDeclarations, idDeclarations) = input.split("\n\n")

    val ranges = rangeDeclarations
        .lines()
        .map { rangeDeclaration ->
            val (start, end) = rangeDeclaration.split("-")

            start.toLong()..end.toLong()
        }
    val ids = idDeclarations
        .lines()
        .map(String::toLong)

    return ids.count { id ->
        ranges.any { range -> id in range }
    }
}

private fun part2(input: String): Int {
    return input.length
}

fun main() {
    val testInput = readInput("day05/test-input.txt")
    val input = readInput("day05/input.txt")

    // part 1
    checkSolution(part1(testInput), 3)
    println(part1(input))

    // part 2
    // checkSolution(part2(testInput), 1)
    // println(part2(input))
}