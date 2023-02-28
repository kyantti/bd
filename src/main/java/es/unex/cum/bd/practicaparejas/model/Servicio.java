package main.java.es.unex.cum.bd.practicaparejas.model;

public class Servicio {
    private int id;
    private String nombre;
    private String descripcion;
    private int idDireccion;
    
    public Servicio(int id, String nombre, String descripcion, int idDireccion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDireccion = idDireccion;
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

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    
}
