package com.example.gestionecolesup.model;

public class Cours {

    public String filiere ;
    public String niveau ;
    public String nom ;
    public String semestre ;
    public String professeur;
    public Cours( String filiere, String niveau, String nom, String  semestre,String professeur) {

        this.filiere = filiere;
        this.niveau = niveau;
        this.nom = nom;
        this.semestre = semestre;
        this.professeur = professeur;
    }
    public Cours(String nom, String  semestre,String professeur) {

        this.nom = nom;
        this.semestre = semestre;
        this.professeur = professeur;
    }
    public String getFiliere() {
        return this.filiere ;
    }
    public String getNiveau() {
        return this.niveau ;
    }
    public String getNom() {
        return this.nom ;
    }
    public String getSemestre() {return this.semestre;}
    public String getProfesseur() {return this.professeur;}

    public void setFiliere(String filiere) {
        this.filiere=filiere;}
    public void setNom(String nom) {
        this.nom=nom;}
    public void setNiveau(String niveau) {
        this.niveau=niveau;}
    public void setSemestre(String semestre) {
        this.semestre=semestre;}
    public void setProfesseur(String professeur) {
        this.professeur=professeur;}




}
