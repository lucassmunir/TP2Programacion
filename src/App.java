import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        // --- 1. Inicialización ---
        Universidad miUniversidad = new Universidad("Universidad de Levantamiento de Pala");

        Estudiante est1 = new Estudiante("Ana", "Gomez", 20, "45123456", "Ingeniería de Software");
        Estudiante est2 = new Estudiante("Carlos", "Arias", 22, "40000000", "Derecho");
        Estudiante est3 = new Estudiante("Laura", "Zapata", 19, "48765432", "Medicina");
        Estudiante est4 = new Estudiante("Juan", "Díaz", 25, "35987654", "Ingeniería de Software");
        
        Profesor prof1 = new Profesor("Roberto", "Pérez", 45, "15789012", "Física Teórica", 15);
        Personal pers1 = new Personal("Marta", "Juárez", 35, "28345678", "Recursos Humanos", "Analista Senior", LocalDate.of(2018, 5, 10));

        // --- 2. Demostración de Polimorfismo y ListaEnlazada ---
        System.out.println("\n===== 1. DEMOSTRACIÓN DE POLIMORFISMO (ListaEnlazada) =====");
        
        // Agregamos a la ListaEnlazada interna de la Universidad
        miUniversidad.agregarMiembro(est1);
        miUniversidad.agregarMiembro(prof1);
        miUniversidad.agregarMiembro(pers1);
        miUniversidad.agregarMiembro(est2);
        miUniversidad.agregarMiembro(est3);
        miUniversidad.agregarMiembro(est4);
        
        miUniversidad.listarTodosLosMiembros();

        // --- 3. Demostración de Promedios (Estudiante con Nodos) ---
        System.out.println("\n===== 2. CALCULO DE PROMEDIOS (Con Nodos) =====");
        
        Materia m1 = new Materia("POO", "PRG101", 6, 85, prof1);
        Materia m2 = new Materia("Bases de Datos", "DB101", 5, 90, prof1);
        Materia m3 = new Materia("Matemática", "MAT101", 7, 70, null);
        
        // Agregamos a la ListaEnlazada interna del Estudiante
        est1.agregarMateria(m1);
        est1.agregarMateria(m2);
        est1.agregarMateria(m3);

        // Llamadas actualizadas a los nuevos métodos
        double promedioIterativo = est1.calcularPromedioIterativo();
        System.out.println("Promedio de " + est1.getNombre() + " (Iterativo): " + String.format("%.2f", promedioIterativo));
        
        double promedioRecursivo = est1.calcularPromedioRecursivo(); 
        System.out.println("Promedio de " + est1.getNombre() + " (Recursivo con Nodos): " + String.format("%.2f", promedioRecursivo));

        // --- 4. Algoritmos sobre la Universidad (Conversión Lista -> Array) ---
        System.out.println("\n===== 3. ALGORITMOS (Conversión Lista -> Array) =====");
        
        String carreraBuscar = "Ingeniería de Software";

        // Usamos los métodos de instancia de Universidad que convierten la lista automáticamente
        int conteo = miUniversidad.contarEstudiantesPorCarreraRecursivo(carreraBuscar);
        System.out.println("Conteo de '" + carreraBuscar + "' (Recursivo): " + conteo);

        // --- 5. Búsqueda y Ordenamiento ---
        System.out.println("\n===== 4. ORDENAMIENTO Y BÚSQUEDA BINARIA =====");
        
        Estudiante[] arrayParaOrdenar = {est1, est2, est3, est4};
        
        System.out.println("--- Array Original (Desordenado) ---");
        for (Estudiante e : arrayParaOrdenar) {
             System.out.println(" |-> " + e.getApellido());
        }
        
        Estudiante[] arrayOrdenado = Universidad.ordenarEstudiantesPorApellido(arrayParaOrdenar);
        
        System.out.println("--- Array Ordenado por Apellido ---");
        for (Estudiante e : arrayOrdenado) {
             System.out.println(" |-> " + e.getApellido());
        }
        
        // Búsqueda Binaria
        String apellidoBuscar = "Díaz"; 
        int indiceEncontrado = Universidad.busquedaBinariaEstudiantes(arrayOrdenado, apellidoBuscar);

        System.out.println("\n--- Búsqueda Binaria de Apellido: " + apellidoBuscar + " ---");
        if (indiceEncontrado != -1) {
            System.out.println("Estudiante encontrado: " + arrayOrdenado[indiceEncontrado].obtenerInformacionCompleta());
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}