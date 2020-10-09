
//fun main() {
//    val animalHouse = Enclosure<Animal>()
//    val catHouse = Enclosure<Cat>()
//    val dogHouse = Enclosure<Dog>()
//
//    moveInCat(animalHouse)
//    moveInCat(catHouse)
//
//    val c: Enclosure<Cat> = animalHouse
//    moveInDog(animalHouse)
//    moveInDog(dogHouse)
//
//    val d: Enclosure<Cat> = animalHouse
//}


open class Animal(val name: String) {
    fun eat() {}
}

class Cat(name: String) : Animal(name) {
    fun purr() {}
}

class Dog(name: String) : Animal(name) {
    fun wof() {}
}

class Enclosure<in T : Animal> {
    private var resident: T? = null

    fun moveInAnimal(animal: T) {
        this.resident = animal
    }

    fun feed() {
        resident?.eat()
    }

    fun foo(): Animal? = resident
}

fun moveInCat(enclosure: Enclosure<Cat>) {
    enclosure.moveInAnimal(Cat("Admiral von Schneider"))
}

fun moveInDog(enclosure: Enclosure<Dog>) {
    enclosure.moveInAnimal(Dog("Dog"))
}
