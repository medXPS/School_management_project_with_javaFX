package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.model.Etudiant;
import com.example.gestionecolesup.model.Recrutement;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;

public interface EtudiantDAO {

    void updateEtudiant(String nom, String prenom, String cne, String tel, LocalDate dateNaissance, String filiere, String niveau);

    ObservableList<Etudiant> ShowEtudiant(String filiere, String niveau);

    void saveetudiant(Etudiant e,String filiere,String niveau);

}
