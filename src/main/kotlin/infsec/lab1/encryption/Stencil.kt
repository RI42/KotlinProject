package infsec.lab1.encryption

object Stencil {

    fun start() {
        println("----- Трафаретный способ -----")
        val msg = "ПРИЕЗЖАЮ ШЕСТОГО" //readLine("сообщение: ")
        val encoded = encode(msg)
        println("Оригинал: $msg")
        println("Шифр:\n$encoded")
    }

    val rotationIndices = listOf(
        listOf(2, 3, 1, 0),
        listOf(0, 1, 3, 2),
        listOf(3, 2, 0, 1),
        listOf(1, 0, 2, 3),
    )

    val size = rotationIndices.size

    fun encode(msg: String): String {
        val array = Array(size) { CharArray(size) }
        msg.padEnd(size * size).take(size * size).chunked(size)
            .forEachIndexed { i, list ->
                list.forEachIndexed { j, c ->
                    array[j][rotationIndices[i][j]] = c
                }
            }
        return array.joinToString("\n") { it.joinToString("") }
    }
}