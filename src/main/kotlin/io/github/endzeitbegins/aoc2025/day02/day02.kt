package io.github.endzeitbegins.aoc2025.day02

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput
import kotlin.math.abs
import kotlin.math.log10

private fun part1(input: String): Long =
    readRanges(input)
        .sumOf { range -> range.countInvalidIDs() }


private fun part2(input: String): Int {
    return input.length
}

private fun readRanges(input: String): List<LongRange> =
    input.split(",")
        .map { range ->
            val (start, end) = range.split("-")
            start.toLong()..end.toLong()
        }

private fun LongRange.countInvalidIDs(): Long {
    val range = this

    val startID = range.firstNotNullOfOrNull { id ->
        "$id".takeIf { it.length % 2 == 0 }
    }
    if (startID == null)
        return 0L

    var sum = 0L

    var number = startID.take(startID.length / 2).toLong()
    var derivedNumber = "$number$number".toLong()

    while (derivedNumber <= range.last) {
        if (derivedNumber in range) {
            sum += derivedNumber
        }
        number += 1
        derivedNumber = "$number$number".toLong()
    }

    return sum
}

fun main() {
    val testInput = readInput("day02/test-input.txt")
    val input = readInput("day02/input.txt")

    // part 1
    checkSolution(part1(testInput), 1227775554L)
    println(part1(input))

    // part 2
    // checkSolution(part2(testInput), 1)
    // println(part2(input))
}