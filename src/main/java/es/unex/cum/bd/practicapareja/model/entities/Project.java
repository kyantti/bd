package es.unex.cum.bd.practicapareja.model.entities;

import java.time.LocalDate;

public class Project {
    private int id;
    private String tittle;
    private String description;
    private LocalDate startDate;
    private int serviceId;

    public Project(){
        
    }

    public Project(int id, String tittle, String description, LocalDate startDate, int serviceId) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.startDate = startDate;
        this.serviceId = serviceId;
    }

    public Project(String tittle, String description, LocalDate startDate, int serviceId) {
        this.tittle = tittle;
        this.description = description;
        this.startDate = startDate;
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", tittle=" + tittle + ", description=" + description + ", startDate=" + startDate
                + ", serviceId=" + serviceId + "]";
    }

}
