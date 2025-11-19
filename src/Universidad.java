public class Universidad {
    private String nombre;
    private ListaEnlazada<MiembroUniversidad> miembros; 

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.miembros = new ListaEnlazada<>();
    }

    public String getNombre() { return nombre; }

    public void agregarMiembro(MiembroUniversidad miembro) {
        if (miembro != null) {
            miembros.agregarAlFinal(miembro);
            System.out.println("Miembro agregado: " + miembro.obtenerRol() + " - " + ((Persona)miembro).getNombre());
        }
    }

    public void listarTodosLosMiembros() {
        System.out.println("\n--- Listado de Miembros de la " + nombre + " ---");
        if (miembros.isEmpty()) {
            System.out.println("No hay miembros registrados.");
            return;
        }
        for (MiembroUniversidad miembro : miembros) {
            System.out.println("-> Rol: " + miembro.obtenerRol());
            System.out.println("   Info: " + miembro.obtenerInformacionCompleta());
        }
        System.out.println("----------------------------------------");
    }
    
    public void buscarPorRol(String rol) {
        System.out.println("\n--- Buscando Miembros con Rol: " + rol + " ---");
        int contador = 0;
        for (MiembroUniversidad miembro : miembros) {
            if (miembro.obtenerRol().equalsIgnoreCase(rol)) {
                System.out.println("   " + miembro.obtenerInformacionCompleta());
                contador++;
            }
        }
        if (contador == 0) System.out.println("No se encontraron miembros con el rol '" + rol + "'.");
        System.out.println("----------------------------------------");
    }

    private Estudiante[] obtenerEstudiantesArray() {
        Object[] todosLosMiembros = miembros.convertirAArray();
        
        int contadorEstudiantes = 0;
        for (Object obj : todosLosMiembros) {
            if (obj instanceof Estudiante) {
                contadorEstudiantes++;
            }
        }

        Estudiante[] arrayEstudiantes = new Estudiante[contadorEstudiantes];
        int index = 0;

        for (Object obj : todosLosMiembros) {
            if (obj instanceof Estudiante) {
                arrayEstudiantes[index] = (Estudiante) obj;
                index++;
            }
        }
        
        return arrayEstudiantes;
    }

    public static int contarEstudiantesIterativo(Estudiante[] estudiantes, String carrera) {
        int contador = 0;
        for (Estudiante e : estudiantes) {
            if (e.getCarrera().equals(carrera)) contador++;
        }
        return contador;
    }

    public static int contarEstudiantesRecursivo(Estudiante[] estudiantes, String carrera, int indice) {
        if (indice >= estudiantes.length) return 0;
        int suma = (estudiantes[indice].getCarrera().equalsIgnoreCase(carrera)) ? 1 : 0;
        return suma + contarEstudiantesRecursivo(estudiantes, carrera, indice + 1);
    }
    
    public int contarEstudiantesPorCarreraRecursivo(String carrera) {
        Estudiante[] estudiantes = obtenerEstudiantesArray(); 
        return contarEstudiantesRecursivo(estudiantes, carrera, 0);
    }

    public Estudiante buscarEstudiantePorDocumentoIterativo(String documento) {
        Estudiante[] estudiantes = obtenerEstudiantesArray();
        for (Estudiante e : estudiantes) {
            if (e.getDocumento() != null && e.getDocumento().equals(documento)) return e;
        }
        return null;
    }

    public static Estudiante[] ordenarEstudiantesPorApellido(Estudiante[] estudiantes) {
        int n = estudiantes.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (estudiantes[j].getApellido().compareTo(estudiantes[min].getApellido()) < 0) {
                    min = j;
                }
            }
            Estudiante temp = estudiantes[i];
            estudiantes[i] = estudiantes[min];
            estudiantes[min] = temp;
        }
        return estudiantes;
    }

    public static int busquedaBinariaEstudiantes(Estudiante[] estudiantes, String apellido) {
        int izq = 0, der = estudiantes.length - 1;
        while (izq <= der) {
            int medio = (izq + der) / 2;
            int comp = apellido.compareTo(estudiantes[medio].getApellido());
            if (comp == 0) return medio;
            if (comp < 0) der = medio - 1;
            else izq = medio + 1;
        }
        return -1;
    }
}