public class Profesor extends Persona implements MiembroUniversidad {
    private String especialidad;
    private int aniosExperiencia;
    private ListaEnlazada<Materia> materiasAsignadas;
    
    public Profesor(String nombre, String apellido, int edad, String documento, 
                    String especialidad, int aniosExperiencia) {
        super(nombre, apellido, edad, documento);
        setEspecialidad(especialidad);
        setAniosExperiencia(aniosExperiencia);
        this.materiasAsignadas = new ListaEnlazada<>();
    }
    
    public Profesor() {
        super();
        this.materiasAsignadas = new ListaEnlazada<>();
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String e) {
        if (e != null && !e.trim().isEmpty()) this.especialidad = e;
        else System.out.println("Especialidad inválida.");
    }

    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int a) {
        if (a >= 0) this.aniosExperiencia = a;
        else System.out.println("Años inválidos.");
    }
    
    public ListaEnlazada<Materia> getMateriasAsignadas() {
        return materiasAsignadas;
    }

    public void asignarMateria(Materia materia) {
        if (materia != null) {
            // CAMBIO: Usamos agregarAlFinal
            materiasAsignadas.agregarAlFinal(materia);
            System.out.println(getNombre() + " " + getApellido() + " ha sido asignado a la materia.");
        }
    }

    @Override
    public String toString() {
        String infoPersona = super.toString();
        return "Profesor: [" + infoPersona + 
               ", Especialidad: " + especialidad + 
               ", Años Exp: " + aniosExperiencia + 
               ", Materias: " + materiasAsignadas.getSize() + "]";
    }

    @Override
    public String obtenerRol() { return "Profesor"; }
    @Override
    public String obtenerInformacionCompleta() { return this.toString(); }
}