package com.example.gestionecolesup.model;

import javafx.scene.control.DatePicker;

public class Conge {
    private String nameemlpoye;
    private String datedebutconge;
    private String cinemlpoye;
    private String nbrdejrs;

    public Conge(String nameemlpoye, String datedebutconge, String cinemlpoye, String nbrdejrs) {
        this.nameemlpoye = nameemlpoye;
        this.datedebutconge = datedebutconge;
        this.cinemlpoye = cinemlpoye;
        this.nbrdejrs = nbrdejrs;
    }



    public String getNameemlpoye() {
        return nameemlpoye;
    }

    public void setNameemlpoye(String nameemlpoye) {
        this.nameemlpoye = nameemlpoye;
    }

    public String getDatedebutconge() {
        return datedebutconge;
    }

    public void setDatedebutconge(String datedebutconge) {
        this.datedebutconge = datedebutconge;
    }

    public String getCinemlpoye() {
        return cinemlpoye;
    }

    public void setCinemlpoye(String cinemlpoye) {
        this.cinemlpoye = cinemlpoye;
    }

    public String getNbrdejrs() {
        return nbrdejrs;
    }

    public void setNbrsdejrs(String nbrsdejrs) {
        this.nbrdejrs = nbrsdejrs;
    }
}
