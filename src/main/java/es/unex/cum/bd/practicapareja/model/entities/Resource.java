package es.unex.cum.bd.practicapareja.model.entities;

public class Resource {
    private int id;
    private String name;
    private String sectionId;
    private int nrpt;

    public Resource(int id, String name, String sectionId, int nrpt) {
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
    public String getSectionId() {
        return sectionId;
    }
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
    public int getNrpt() {
        return nrpt;
    }
    public void setNrpt(int nrpt) {
        this.nrpt = nrpt;
    }

    
}
