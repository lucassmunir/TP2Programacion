public class Estudiante extends Persona implements MiembroUniversidad {
    private String carrera;
    private double promedio;
    
    private ListaEnlazada<Materia> materias;

    // Constructor Default
    public Estudiante() {
        super();
        this.materias = new ListaEnlazada<>();
    }

    // Constructor Completo
    public Estudiante(String nombre, String apellido, int edad, String documento, String carrera) {
        super(nombre, apellido, edad, documento);
        setCarrera(carrera);
        this.materias = new ListaEnlazada<>();
    }

    // --- Getters y Setters ---
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) {
        if(carrera != null && !carrera.trim().isEmpty()) this.carrera = carrera;
        else System.out.println("La carrera no puede estar vacía.");
    }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) {
        if(promedio >= 0.0 && promedio <= 10.0) this.promedio = promedio;
        else System.out.println("El promedio debe estar entre 0.0 y 10.0.");
    }

    public ListaEnlazada<Materia> getMaterias() {
        return materias;
    }

    // --- Métodos ---

    public void agregarMateria(Materia materia) {
        materias.agregarAlFinal(materia);
    }

    public double calcularPromedioIterativo() {
        if(materias.isEmpty()) {
            this.promedio = 0;
            return 0.0;
        } else {
            double suma = 0.0;
            for(Materia m : materias) {
                suma += m.getCalificacion();
            }
            double nuevoPromedio = suma / materias.getSize();
            this.promedio = nuevoPromedio;
            return nuevoPromedio;
        }
    }
    
    public void calcularPromedio() {
        this.calcularPromedioIterativo();
    }

    public static double calcularPromedioRecursivo(Nodo<Materia> actual, int contador, double suma) {
        if (actual == null) {
            return contador == 0 ? 0 : suma / contador;
        }
        
        Materia materia = actual.getDato();
        
        return calcularPromedioRecursivo(actual.getSiguiente(), contador + 1, suma + materia.getCalificacion());
    }
    
    public double calcularPromedioRecursivo() {
        this.promedio = calcularPromedioRecursivo(this.materias.getHead(), 0, 0.0);
        return this.promedio;
    }
    // ------------------------------------------------------------

    @Override
    public String toString() {
        String infoPersona = super.toString();
        return "Estudiante: [" + infoPersona + 
               ", Carrera: " + carrera + 
               ", Promedio: " + String.format("%.2f", promedio) + 
               ", Total Materias: " + materias.getSize() + "]";
    }

    @Override
    public String obtenerRol() { return "Estudiante"; }
    @Override
    public String obtenerInformacionCompleta() { return this.toString(); }
    @Override
    public void setEdad(int edad) {
        if(edad > 17) super.setEdad(edad);
        else System.out.println("Edad inválida para estudiante.");
    }
}