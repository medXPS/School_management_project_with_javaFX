package com.example.gestionecolesup.model;

public class Admin {
        private String nom ;
        private String prenom ;
        private String nomUtilisateur ;

        private  String  tel ;
        private  String motDePass;
        public Admin( String nom, String  prenom, String  NomUtilisateur, String tel , String MotDePass){
            this.nom=nom;
            this.prenom=prenom;
            this.nomUtilisateur=NomUtilisateur;

            this.tel=tel ;

            this.motDePass=MotDePass;

        }

    public String getNom() {
            return this.nom ;
    }

    public String getPrenom() {
            return this.prenom;

    }

    public String getNomUtilisateur() {
            return this.nomUtilisateur;
    }



    public String getTel() {
            return this.tel;

    }

    public String getMotDePass() {
            return this.motDePass;

    }
}



