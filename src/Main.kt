fun main() {
    // Lista para almacenar las mascotas registradas
    val listaMascotas = mutableListOf<Mascota>()
    // Lista para almacenar las consultas médicas realizadas
    val listaConsultas = mutableListOf<ConsultaMedica>()

    // Función para registrar una nueva mascota
    fun registrarMascota() {
        println("Registro de nueva mascota:")
        print("Nombre: ")
        val nombre = readLine() ?: ""
        print("Especie: ")
        val especie = readLine() ?: ""
        print("Edad: ")
        val edad = readLine()?.toIntOrNull() ?: 0
        print("Peso: ")
        val peso = readLine()?.toDoubleOrNull() ?: 0.0

        val mascota = Mascota(nombre, especie, edad, peso)
        listaMascotas.add(mascota)
        println("Mascota registrada con éxito: ${mascota.describir()}\n")
    }

    // Función para registrar una consulta médica para una mascota específica
    fun registrarConsultaMedica() {
        if (listaMascotas.isEmpty()) {
            println("No hay mascotas registradas. Registre una mascota primero.\n")
            return
        }

        println("Seleccione una mascota para la consulta médica:")
        listaMascotas.forEachIndexed { index, mascota ->
            println("${index + 1}. ${mascota.describir()}")
        }
        print("Ingrese el número de la mascota: ")
        val indiceMascota = readLine()?.toIntOrNull()?.minus(1) ?: return

        if (indiceMascota !in listaMascotas.indices) {
            println("Índice inválido.\n")
            return
        }

        val mascota = listaMascotas[indiceMascota]
        print("Diagnóstico: ")
        val diagnostico = readLine() ?: ""
        print("Costo de la consulta: ")
        val costo = readLine()?.toDoubleOrNull() ?: 0.0
        print("¿Incluye medicación? (s/n): ")
        val incluyeMedicacion = readLine()?.equals("s", ignoreCase = true) ?: false

        val consulta = ConsultaMedica(mascota.nombre, mascota.especie, mascota.edad, mascota.peso)
        consulta.diagnostico = diagnostico
        consulta.costo = costo
        listaConsultas.add(consulta)

        println("Consulta médica registrada con éxito:")
        println(consulta.describir())
        println("Costo total: $${consulta.calcularCosto(incluyeMedicacion)}\n")
    }

    // Función para mostrar el historial de consultas de una mascota
    fun mostrarHistorialConsultas() {
        if (listaMascotas.isEmpty()) {
            println("No hay mascotas registradas.\n")
            return
        }

        println("Seleccione una mascota para ver su historial de consultas:")
        listaMascotas.forEachIndexed { index, mascota ->
            println("${index + 1}. ${mascota.describir()}")
        }
        print("Ingrese el número de la mascota: ")
        val indiceMascota = readLine()?.toIntOrNull()?.minus(1) ?: return

        if (indiceMascota !in listaMascotas.indices) {
            println("Índice inválido.\n")
            return
        }

        val mascota = listaMascotas[indiceMascota]
        val consultasMascota = listaConsultas.filter { it.nombre == mascota.nombre }

        if (consultasMascota.isEmpty()) {
            println("No hay consultas registradas para ${mascota.nombre}.\n")
            return
        }

        println("Historial de consultas para ${mascota.nombre}:")
        consultasMascota.forEach { consulta ->
            println(consulta.describir())
        }
        println()
    }

    // Función para modificar los datos de una mascota
    fun modificarMascota() {
        if (listaMascotas.isEmpty()) {
            println("No hay mascotas registradas.\n")
            return
        }

        println("Seleccione una mascota para modificar:")
        listaMascotas.forEachIndexed { index, mascota ->
            println("${index + 1}. ${mascota.describir()}")
        }
        print("Ingrese el número de la mascota: ")
        val indiceMascota = readLine()?.toIntOrNull()?.minus(1) ?: return

        if (indiceMascota !in listaMascotas.indices) {
            println("Índice inválido.\n")
            return
        }

        val mascota = listaMascotas[indiceMascota]
        println("Modificando datos de ${mascota.nombre}:")
        print("Nuevo peso (actual: ${mascota.peso}): ")
        val nuevoPeso = readLine()?.toDoubleOrNull()
        if (nuevoPeso != null) mascota.actualizarPeso(nuevoPeso)

        print("¿Incrementar edad? (s/n): ")
        val incrementarEdad = readLine()?.equals("s", ignoreCase = true) ?: false
        if (incrementarEdad) mascota.incrementarEdad()

        println("Datos actualizados: ${mascota.describir()}\n")
    }

    // Función para calcular el costo total de todas las consultas de una mascota
    fun calcularCostoTotalConsultas() {
        if (listaMascotas.isEmpty()) {
            println("No hay mascotas registradas.\n")
            return
        }

        println("Seleccione una mascota para calcular el costo total de sus consultas:")
        listaMascotas.forEachIndexed { index, mascota ->
            println("${index + 1}. ${mascota.describir()}")
        }
        print("Ingrese el número de la mascota: ")
        val indiceMascota = readLine()?.toIntOrNull()?.minus(1) ?: return

        if (indiceMascota !in listaMascotas.indices) {
            println("Índice inválido.\n")
            return
        }

        val mascota = listaMascotas[indiceMascota]
        val consultasMascota = listaConsultas.filter { it.nombre == mascota.nombre }

        if (consultasMascota.isEmpty()) {
            println("No hay consultas registradas para ${mascota.nombre}.\n")
            return
        }

        val costoTotal = consultasMascota.sumOf { it.costo }
        println("Costo total de consultas para ${mascota.nombre}: $$costoTotal\n")
    }

    // Menú principal
    while (true) {
        println("Sistema de Gestión Veterinaria")
        println("1. Registrar nueva mascota")
        println("2. Registrar consulta médica")
        println("3. Mostrar historial de consultas de una mascota")
        println("4. Modificar datos de una mascota")
        println("5. Calcular costo total de consultas de una mascota")
        println("6. Salir")
        print("Seleccione una opción: ")
        when (readLine()?.toIntOrNull()) {
            1 -> registrarMascota()
            2 -> registrarConsultaMedica()
            3 -> mostrarHistorialConsultas()
            4 -> modificarMascota()
            5 -> calcularCostoTotalConsultas()
            6 -> {
                println("Saliendo del sistema...")
                return
            }
            else -> println("Opción inválida. Intente nuevamente.\n")
        }
    }
}