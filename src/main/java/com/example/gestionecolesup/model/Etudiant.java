package com.example.gestionecolesup.model;


public class Etudiant {

    public String filiere ;
    public String niveau ;
    public String nom ;
    public String prenom ;
    public String cne ;
    public String  tel ;
    public  String date_naissance;

    public Etudiant(String nom, String  prenom, String cne, String tel, String date_naissance){
        this.nom=nom;
        this.prenom=prenom;
        this.cne=cne;
        this.tel= tel;
        this.date_naissance= date_naissance;

    }
    public Etudiant(String filiere, String niveau, String nom, String  prenom, String cne, String tel, String date_naissance){

        this.filiere=filiere;
        this.niveau=niveau;
        this.nom=nom;
        this.prenom=prenom;
        this.cne=cne;
        this.tel= tel;
        this.date_naissance= date_naissance;

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

    public String getPrenom() {
        return this.prenom;

    }

    public String getCne() {
        return this.cne;
    }



    public String getTel() {
        return this.tel;

    }

    public String getDate_naissance() {
        return this.date_naissance;

    }


    public void setFiliere(String filiere) {
        filiere=filiere;
    }
    public void setNiveau(String niveau) {
        niveau=niveau;
    }
    public void setNom(String nom) {
        nom=nom;
    }
    public void setPrenom(String prenom) {
        prenom=prenom;
    }
    public void setTel(String tel) {
        tel=tel;
    }
    public void setDate_naissance(String date) {
        date_naissance= date_naissance;
    }

    public void setCne(String cne) {
        cne=cne;
    }
}


