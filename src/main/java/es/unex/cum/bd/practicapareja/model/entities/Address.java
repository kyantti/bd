package es.unex.cum.bd.practicapareja.model.entities;

public class Address {

    private int id;
    private String denomination;

    public Address(int id, String denomination) {
        this.id = id;
        this.denomination = denomination;
    }

    public Address(String denomination) {
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

    @Override
    public String toString() {
        return "Address [id=" + id + ", denomination=" + denomination + "]";
    }

}

