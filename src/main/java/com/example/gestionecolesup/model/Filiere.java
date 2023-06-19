package com.example.gestionecolesup.model;

public class Filiere {
    private String nomDuFiliere;
    private String nomDuResponsable;
    private String niveau;
    private  String nombreEtudiants;
    private String nombreModules;
    private String Semestre;
    private String id;

    public Filiere(String id ,String nomDuFiliere, String niveau, String nomDuResponsable, String nombreEtudiants, String nombreModules, String Semestre) {
        this.nomDuFiliere = nomDuFiliere;
        this. niveau = niveau;
        this.nomDuResponsable = nomDuResponsable;
        this.nombreEtudiants = nombreEtudiants;
        this.nombreModules = nombreModules;
        this.Semestre = Semestre;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getNomDuFiliere() {
        return nomDuFiliere;
    }

    public String getNomDuResponsable() {
        return nomDuResponsable;
    }

    public String getNombreEtudiants() {
        return nombreEtudiants;
    }

    public String getNombreModules() {
        return nombreModules;
    }

    public String getSemestre() {
        return this.Semestre;
    }}



