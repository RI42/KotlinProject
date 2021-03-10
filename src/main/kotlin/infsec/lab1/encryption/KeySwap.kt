package infsec.lab1.encryption

import kotlin.math.ceil

object KeySwap {

    data class TableRow(val keyChar: Char, val origPos: Int, val naturePos: Int, val msg: String) {
        override fun toString(): String {
            return "$keyChar|$origPos|$naturePos|$msg"
        }
    }

    fun start() {
        println("----- Перестановка по ключу -----")
        val msg = "НЕЯСНОЕ СТАНОВИТСЯ ЕЩЕ БОЛЕЕ НЕПОНЯТНЫМ" // readLine("предлжение: ")
        val key = "лунатик" // readLine("ключ: ")
        val keyTable = key.asSequence()
            .mapIndexed { index, c -> c to index }
            .sortedBy { it.first }
            .mapIndexed { index, pair -> Triple(pair.first, pair.second, index) }
            .sortedBy { it.second }
            .toList()

        val filteredMsg = msg.filterNot { it.isWhitespace() }
        val tableWidth = ceil(filteredMsg.length.toDouble() / key.length).toInt()
        val msgChunks = filteredMsg.chunked(tableWidth) { it.padEnd(tableWidth).toString() }
        val msgTable = keyTable.zip(msgChunks) { a, b -> TableRow(a.first, a.second, a.third, b) }
        val swapedTable = msgTable.sortedBy { it.naturePos }.map { it.msg }

        val cipher = buildString {
            for (i in 0 until tableWidth) {
                for (j in msgTable.indices) {
                    append(swapedTable[j][i])
                }
            }
        }
        println(msgTable.joinToString("\n"))
        println()
        println(msgTable.sortedBy { it.naturePos }.joinToString("\n"))
        println()
        println("Оригинал: $msg")
        println("Шифр: $cipher")
    }
}