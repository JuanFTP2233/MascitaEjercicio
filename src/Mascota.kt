open class Mascota {
    var nombre: String = ""
    var especie: String = ""
    var edad: Int = 0
    var peso: Double = 0.0

    constructor()
    constructor(nombre: String, especie: String, edad: Int, peso: Double) {
        this.nombre = nombre
        this.especie = especie
        this.edad = edad
        this.peso = peso
    }

    fun actualizarPeso(nuevoPeso: Double) {
        peso = nuevoPeso
    }

    fun incrementarEdad() {
        edad++
    }

    open fun describir(): String {
        return "Nombre: $nombre, Especie: $especie, Edad: $edad años, Peso: $peso kg"
    }

    override fun toString(): String {
        return "Mascota(nombre='$nombre', especie='$especie', edad=$edad, peso=$peso)"
    }
}