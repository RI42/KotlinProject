package infsec.lab1.encryption

class Playfair(private val key: String) {

    companion object {
        fun start() {
            println("----- Метод биграмм -----")
            val msg = "ПУСТЬ КОНСУЛЫ БУДУТ БДИТЕЛЬНЫ"  //readLine("сообщение: ")
            val key = "РЕСПУБЛИКА" //readLine("ключ: ")
            val algo = Playfair(key)
            val cipher = algo.encode(msg)
            println(algo.alphabet.joinToString("\n") { it.joinToString("") })
            println()
            println("Оригинал: $msg")
            println("Шифр: $cipher")
        }
    }

    private val rows = 5
    private val cols = 6

    // ключ + оставшиеся буквы алфавита (за некоторым исключением)
    val alphabet = kotlin.run {
        val f = { c: Char -> c !in "ъйё" }
        (key.toLowerCase().filter(f).toSet() + ('а'..'я').filter(f)).chunked(cols)
    }

    // индексы букв
    val indices = alphabet
        .flatMapIndexed { row, list ->
            list.mapIndexed { col, c -> c to (row to col) }
        }
        .toMap()

    fun encode(msg: String): String = msg.toLowerCase().filter { !it.isWhitespace() }
        .chunked(2) { it[0] to it.getOrElse(1) { 'х' } }
        .joinToString("") { (a, b) ->
            val firstInd = indices[a]!!
            val secondInd = indices[b]!!
            when {
                // обе буквы биграммы исходного текста принадлежать одной строке таблицы
                firstInd.first == secondInd.first -> buildString {
                    append(alphabet[firstInd.first][firstInd.second.plus(1).rem(cols)])
                    append(alphabet[secondInd.first][secondInd.second.plus(1).rem(cols)])
                }
                // обе буквы биграммы исходного текста принадлежать одной колонке таблицы
                firstInd.second == secondInd.second -> buildString {
                    append(alphabet[firstInd.first.plus(1).rem(rows)][firstInd.second])
                    append(alphabet[secondInd.first.plus(1).rem(rows)][secondInd.second])
                }
                // прямоугольник
                else -> buildString {
                    append(alphabet[firstInd.first][secondInd.second])
                    append(alphabet[secondInd.first][firstInd.second])
                }
            }
        }
}
