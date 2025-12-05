package io.github.endzeitbegins.aoc2025.day05

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput

private fun part1(input: String): Int {
    val (rangeDeclarations, idDeclarations) = input.split("\n\n")

    val ranges = parseRanges(rangeDeclarations)
    val ids = parseIDs(idDeclarations)

    return ids.count { id ->
        ranges.any { range -> id in range }
    }
}

private fun part2(input: String): Long {
    var highestFreshID = -1L
    var freshCount = 0L

    val (rangeDeclarations, _) = input.split("\n\n")
    val ranges = parseRanges(rangeDeclarations)
        .sortedBy(LongRange::first)

    for (range in ranges) {
        val lower = maxOf(range.first, highestFreshID + 1)
        val upper = range.last

        if (upper > highestFreshID) {
            freshCount += upper - lower + 1
            highestFreshID = upper
        }
    }

    return freshCount
}

fun main() {
    val testInput = readInput("day05/test-input.txt")
    val input = readInput("day05/input.txt")

    // part 1
    checkSolution(part1(testInput), 3)
    println(part1(input))

    // part 2
     checkSolution(part2(testInput), 14)
     println(part2(input))
}

private fun parseRanges(rangeDeclarations: String): List<LongRange> =
    rangeDeclarations
        .lines()
        .map { rangeDeclaration ->
            val (start, end) = rangeDeclaration.split("-")

            start.toLong()..end.toLong()
        }

private fun parseIDs(idDeclarations: String): List<Long> =
    idDeclarations
        .lines()
        .map(String::toLong)
