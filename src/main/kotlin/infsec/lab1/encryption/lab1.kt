package infsec.lab1.encryption

fun startLab1() {
    PolybiusSquare.start()
    KeySwap.start()
    Playfair.start()
    Stencil.start()
    TwoTables.start()
}


fun readLine(msg: String): String {
    print(msg)
    return readLine().orEmpty()
}