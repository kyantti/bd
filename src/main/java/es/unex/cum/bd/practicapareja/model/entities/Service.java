package es.unex.cum.bd.practicapareja.model.entities;

public class Service {
    private int id;
    private String denomination;
    private int addressId;

    public Service(int id, String denomination, int addressId) {
        this.id = id;
        this.denomination = denomination;
        this.addressId = addressId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

}
