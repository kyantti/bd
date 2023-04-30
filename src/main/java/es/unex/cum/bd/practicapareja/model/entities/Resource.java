package es.unex.cum.bd.practicapareja.model.entities;

public class Resource {
    private int id;
    private String name;
    private int sectionId;
    private int nrpt;

    public Resource(int id, String name, int sectionId, int nrpt) {
        this.id = id;
        this.name = name;
        this.sectionId = sectionId;
        this.nrpt = nrpt;
    }

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
    public int getSectionId() {
        return sectionId;
    }
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
    public int getNrpt() {
        return nrpt;
    }
    public void setNrpt(int nrpt) {
        this.nrpt = nrpt;
    }

    
}
