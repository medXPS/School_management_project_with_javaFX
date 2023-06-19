package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.model.Admin;
import javafx.event.ActionEvent;

public interface profileDAO {
    void modifier_tel( ActionEvent event, String mdp,String ancien_tel, String noveau_tel);
    void modifier_mdp( ActionEvent event, String mdp, String nomUtilisateur );
    void modifier_nomUtilisateur( ActionEvent event  , String nomUtilisateur0,String nomUtilisateur1 ,String mdp );


}
