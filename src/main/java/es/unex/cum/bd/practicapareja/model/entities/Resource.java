package es.unex.cum.bd.practicapareja.model.entities;

import java.util.List;

public class Resource {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Section section;
    private List <Ascription> ascription;
    
    public Resource(int id, String name, String surname, String phoneNumber, String email, Section section,
            List<Ascription> ascription) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.section = section;
        this.ascription = ascription;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Ascription> getAscription() {
        return ascription;
    }

    public void setAscription(List<Ascription> ascription) {
        this.ascription = ascription;
    }

    

}

