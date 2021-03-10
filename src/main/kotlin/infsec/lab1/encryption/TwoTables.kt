package infsec.lab1.encryption

import kotlin.random.Random

object TwoTables {

    fun start() {
        println("----- Метод двух таблиц -----")
        val msg = "ПУСТЬ КОНСУЛЫ БУДУТ БДИТЕЛЬНЫ"  //readLine("сообщение: ")
        val cipher = encode(msg)
        println(alphabetTable1.joinToString("\n") { it.joinToString("") })
        println()
        println(alphabetTable2.joinToString("\n") { it.joinToString("") })
        println()
        println("Оригинал: $msg")
        println("Шифр: $cipher")
    }

    private val rows = 6
    private val cols = 6

    // создаёт рандомно заполненную таблицу из русского алфавита и " :,."
    private fun createAlphabet(seed: Int) = (('а'..'я') + " :,.".toList()).shuffled(Random(seed)).chunked(cols)
    private fun createIndices(alphabet: List<List<Char>>) = alphabet
        .flatMapIndexed { row, list ->
            list.mapIndexed { col, c -> c to (row to col) }
        }
        .toMap()

    val alphabetTable1 = createAlphabet(1)
    val alphabetTable2 = createAlphabet(2)

    val indices1 = createIndices(alphabetTable1)
    val indices2 = createIndices(alphabetTable2)

    fun encode(msg: String): String = msg.toLowerCase()
        .chunked(2) { it[0] to it.getOrElse(1) { '.' } }
        .joinToString("") { (a, b) ->
            val firstInd = indices1[a]!!
            val secondInd = indices2[b]!!
            when {
                firstInd.first == secondInd.first ||
                        firstInd.second == secondInd.second -> buildString {
                    append(alphabetTable2[firstInd.first][firstInd.second])
                    append(alphabetTable1[secondInd.first][secondInd.second])
                }
                else -> buildString {
                    append(alphabetTable2[secondInd.first][firstInd.second])
                    append(alphabetTable1[firstInd.first][secondInd.second])
                }
            }
        }
}
