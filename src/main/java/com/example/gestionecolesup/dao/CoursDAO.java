package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.model.Cours;
import javafx.collections.ObservableList;

public interface CoursDAO {
    void updatecours(String nomducours, String semestre, String professeur, String filiere, String niveau);

    ObservableList<Cours> Showcours(String filiere, String niveau);

    void savecours(Cours c,String filiere,String niveau);


}
