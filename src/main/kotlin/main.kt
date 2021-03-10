import infsec.lab1.encryption.PolybiusSquare
import infsec.lab1.encryption.startLab1

fun main() {
    startLab1()

    val l1 = listOf(
        0 to 2,
        1 to 3,
        2 to 1,
        3 to 0
    )

    val l2 = listOf(
        0 to 0,
        1 to 1,
        2 to 3,
        3 to 2
    )

    val l3 = listOf(
        0 to 3,
        1 to 2,
        2 to 0,
        3 to 1
    )
    val l4 = listOf(
        0 to 1,
        1 to 0,
        2 to 2,
        3 to 3
    )

}
