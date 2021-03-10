package infsec.lab1.encryption

import kotlin.math.ceil

object KeySwap {

    data class TableRow(val keyChar: Char, val origPos: Int, val msg: String) {
        override fun toString(): String {
            return "$keyChar|$origPos|$msg"
        }
    }

    fun start() {
        println("----- Перестановка по ключу -----")
        val msg = "НЕЯСНОЕ СТАНОВИТСЯ ЕЩЕ БОЛЕЕ НЕПОНЯТНЫМ" // readLine("предлжение: ")
        val key = "лунатик" // readLine("ключ: ")
        val keyTable = key.mapIndexed { index, c -> c to index }

        val filteredMsg = msg.filterNot { it.isWhitespace() } // убираем пробелы
        // ширина таблицы сообщения
        val tableWidth = ceil(filteredMsg.length.toDouble() / key.length).toInt()
        val msgChunks = filteredMsg.chunked(tableWidth) { it.padEnd(tableWidth).toString() }
        val msgTable = keyTable.zip(msgChunks) { a, b -> TableRow(a.first, a.second, b) }
        // сортируем ключ по алфавиту, шифруя сообщение
        val swapedTable = msgTable.sortedBy { it.keyChar }.map { it.msg }

        // формируем конечное зашифровонное сообщение
        // обход таблицы с сообщением сверху вниз, слева направо
        val cipher = buildString {
            for (i in 0 until tableWidth) {
                for (j in msgTable.indices) {
                    append(swapedTable[j][i])
                }
            }
        }
        println(msgTable.joinToString("\n"))
        println()
        println(msgTable.sortedBy { it.keyChar }.joinToString("\n"))
        println()
        println("Оригинал: $msg")
        println("Шифр: $cipher")
    }
}