package main.java.es.unex.cum.bd.practicaparejas.model;

import java.util.Date;

public class Desarrollo {
    private Subproyecto subproyecto;
    private Seccion seccion;
    private Recurso recurso;
    private Date fechaAdscripcion;
    
    public Desarrollo(Subproyecto subproyecto, Seccion seccion, Recurso recurso, Date fechaAdscripcion) {
        this.subproyecto = subproyecto;
        this.seccion = seccion;
        this.recurso = recurso;
        this.fechaAdscripcion = fechaAdscripcion;
    }

    public Subproyecto getSubproyecto() {
        return subproyecto;
    }

    public void setSubproyecto(Subproyecto subproyecto) {
        this.subproyecto = subproyecto;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Date getFechaAdscripcion() {
        return fechaAdscripcion;
    }

    public void setFechaAdscripcion(Date fechaAdscripcion) {
        this.fechaAdscripcion = fechaAdscripcion;
    }
       
}
