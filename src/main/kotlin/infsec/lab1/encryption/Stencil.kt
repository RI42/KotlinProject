package infsec.lab1.encryption

object Stencil {

    fun start() {
        println("----- Трафаретный способ -----")
        val msg = "ПРИЕЗЖАЮ ШЕСТОГО" //readLine("сообщение: ")
        val encoded = encode(msg)
        println("Оригинал: $msg")
        println("Шифр:\n$encoded")
    }
    // индексы столбцов трафарета
    // первая строка 0 градусов поворот, вторая 90 градусов и тд
    val rotationIndices = listOf(
        listOf(2, 3, 1, 0),
        listOf(0, 1, 3, 2),
        listOf(3, 2, 0, 1),
        listOf(1, 0, 2, 3),
    )

    val size = rotationIndices.size

    fun encode(msg: String): String {
        val array = Array(size) { CharArray(size) }
        msg.padEnd(size * size) // дополняем пустыми символами до размера size * size (если меньше)
            .take(size * size) // обрезаем до размера size * size (если больше)
            .chunked(size)
            .forEachIndexed { i, list ->
                list.forEachIndexed { j, c ->
                    // прорези заполняются сверху вниз
                    // В rotationIndices[i][j] второй индекс прорези в шаблоне
                    // j всегда 0, 1, 2, 3
                    array[j][rotationIndices[i][j]] = c
                }
            }
        return array.joinToString("\n") { it.joinToString("") }
    }
}


/*

Объяснение на пальцах, что такое rotationIndices

Это индексы прорезей в трафарете в порядке их обхода (заполнения)
В rotationIndices взяты только вторые значения, потому что первые всегда одинаковые

    val rotation0 = listOf(
        0 to 2,
        1 to 3,
        2 to 1,
        3 to 0
    )

    val rotation90 = listOf(
        0 to 0,
        1 to 1,
        2 to 3,
        3 to 2
    )

    val rotation180 = listOf(
        0 to 3,
        1 to 2,
        2 to 0,
        3 to 1
    )
    val rotation270 = listOf(
        0 to 1,
        1 to 0,
        2 to 2,
        3 to 3
    )

 */
