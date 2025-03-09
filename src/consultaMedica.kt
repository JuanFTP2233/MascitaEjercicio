class ConsultaMedica(nombre: String,especie: String, edad: Int, peso: Double) : Mascota(nombre,especie,edad,peso) {

    var costo = 0.0
    var diagnostico = ""



    fun calcularCosto(incluyeMedicacion: Boolean): Double {
        return if (incluyeMedicacion) costo * 1.15 else costo
    }

    override fun describir(): String {
        return "${super.describir()}, Diagnóstico: $diagnostico, Costo: $$costo"
    }

    override fun toString(): String {
        return "ConsultaMedica(costo=$costo, diagnostico='$diagnostico')"
    }
}