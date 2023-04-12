package es.unex.cum.bd.practicapareja.model.entities;

import java.util.Date;

public class Ascription {

    private int sectionId;
    private int resourceId;
    private Date ascriptionDate;

    public Ascription(int sectionId, int resourceId, Date ascriptionDate) {
        this.sectionId = sectionId;
        this.resourceId = resourceId;
        this.ascriptionDate = ascriptionDate;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Date getAscriptionDate() {
        return ascriptionDate;
    }

    public void setAscriptionDate(Date ascriptionDate) {
        this.ascriptionDate = ascriptionDate;
    }
}
