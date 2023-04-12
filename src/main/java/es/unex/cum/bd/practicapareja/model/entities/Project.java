package es.unex.cum.bd.practicapareja.model.entities;

import java.util.Date;
import java.util.List;

public class Project {
    
    private int id;
    private String name;
    private Date startDate;
    private Date endDate; 
    private Service service;
    private List <Subproject> subprojects;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }
    public List<Subproject> getSubprojects() {
        return subprojects;
    }
    public void setSubprojects(List<Subproject> subprojects) {
        this.subprojects = subprojects;
    }
    
    
    
}

