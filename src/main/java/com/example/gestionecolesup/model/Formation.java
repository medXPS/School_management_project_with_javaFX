package com.example.gestionecolesup.model;



public class Formation {
    private String object;
    private String formateur;
    private String description;
    private String date;

    public Formation(String object, String formateur, String description, String date) {
        this.object = object;
        this.formateur = formateur;
        this.description = description;
        this.date = date;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormamteur(String formamteur) {
        this.formateur = formamteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {this.date = date;
    }
}
