package es.unex.cum.bd.practicapareja.model.entities;

import java.time.LocalDate;

public class Ascription {
    private int sectionId;
    private int resourceId;
    private LocalDate ascriptionDate;
    
    public Ascription(int sectionId, int resourceId, LocalDate ascriptionDate) {
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

    public LocalDate getAscriptionDate() {
        return ascriptionDate;
    }

    public void setAscriptionDate(LocalDate ascriptionDate) {
        this.ascriptionDate = ascriptionDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sectionId;
        result = prime * result + resourceId;
        result = prime * result + ((ascriptionDate == null) ? 0 : ascriptionDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ascription other = (Ascription) obj;
        if (sectionId != other.sectionId)
            return false;
        if (resourceId != other.resourceId)
            return false;
        if (ascriptionDate == null) {
            if (other.ascriptionDate != null)
                return false;
        } else if (!ascriptionDate.equals(other.ascriptionDate))
            return false;
        return true;
    }

    
}
