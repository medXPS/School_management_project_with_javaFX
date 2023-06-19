package com.example.gestionecolesup.dao;
import javafx.collections.ObservableList;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;
public interface Formationdao {
    public void saveFormation(Formation f);

    public ObservableList<Formation> ShowListFormation();


}