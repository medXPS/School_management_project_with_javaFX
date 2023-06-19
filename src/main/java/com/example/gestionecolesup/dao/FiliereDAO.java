package com.example.gestionecolesup.dao;


import com.example.gestionecolesup.model.Admin;
import com.example.gestionecolesup.model.Filiere;
import com.example.gestionecolesup.model.Formation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.List;

public interface FiliereDAO {

    public  void saveFiliere(Filiere f);
    public ObservableList<Filiere> ShowListFiliere();

    void UpdateFiliere(String id, String nomDuFiliere, String nomDuResponsable, String niveau, String nombreEtudiants, String nombreModules, String Semestre);

   // void UpdateFiliere(Filiere f);


   // void UpdateFiliere(String id, String nomDuFiliere, String nomDuResponsable, String niveau, String nombreEtudiants, String nombreModules, String semestre);
}
