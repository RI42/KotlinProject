package infsec.lab1.encryption

object PolybiusSquare {

    fun start() {
        println("----- Полибианский квадрат -----")
        val msg = "BARRACUDA" // readLine("Слово для шифрования: ").ifEmpty { "BARRACUDA" }
        val encoded = encode(msg)
        val decoded = decode(encoded)
        println(alphabet)
        println("Оригинал: $msg")
        println("Шифр: $encoded")
    }

    //Полибианский квадрат
    val alphabet: String = """
        qrwty
        iamej
        opslf
        xnzgd
        kubch
        
    """.trimIndent()

    private val indices = alphabet.mapIndexed { index, c -> c to index }.toMap()

    // ширина квадрата
    val width = alphabet.indexOf('\n') + 1

    fun encode(msg: String): String = msg.toLowerCase().map {
        val pos = (indices[it]!! + width) % alphabet.length
        alphabet[pos]
    }.joinToString(separator = "")

    fun decode(cipher: String): String = cipher.map {
        val pos = (indices[it]!! - width + alphabet.length) % alphabet.length
        alphabet[pos]
    }.joinToString(separator = "")

}