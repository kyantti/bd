package main.java.es.unex.cum.bd.practicaparejas.model;

public class Subproyecto {
    private int id;
    private String nombre;
    private String descripcion;
    private int idProyecto;
    
    public Subproyecto(int id, String nombre, String descripcion, int idProyecto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idProyecto = idProyecto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    
}
