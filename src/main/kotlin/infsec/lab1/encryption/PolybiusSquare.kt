package infsec.lab1.encryption

object PolybiusSquare {

    fun start() {
        println("----- Полибианский квадрат -----")
        val msg = "BARRACUDA" // readLine("Слово для шифрования: ").ifEmpty { "BARRACUDA" }
        val encoded = encode(msg)
        println(alphabetTable.joinToString("\n") { it.joinToString("") })
        println("Оригинал: $msg")
        println("Шифр: $encoded")
    }

    //Полибианский квадрат
    val alphabet = "qrwtyiamejopslfxnzgdkubch"
    val width = 5

    val alphabetTable = alphabet.chunked(width).map { it.toList() }

    private val indices = alphabetTable
        .flatMapIndexed { i, list ->
            // запоминаем индекс каждой буквы из таблицы
            list.mapIndexed { j, c -> c to (i to j) }
        }
        .toMap()

    fun encode(msg: String): String = msg.toLowerCase().map {
        val (i, j) = indices[it]!!
        val newPos = (i + 1) % alphabetTable.size
        alphabetTable[newPos][j] // берём нижнюю букву
    }.joinToString(separator = "")
}

