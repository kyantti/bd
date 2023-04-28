package es.unex.cum.bd.practicapareja.model.entities;

public class Section {
    private int id;
    private String denomination;

    public Section(int id, String denomination) {
        this.id = id;
        this.denomination = denomination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    
}
