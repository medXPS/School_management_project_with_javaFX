package com.example.gestionecolesup.dao;
import com.example.gestionecolesup.model.Admin;
import javafx.event.ActionEvent;

import java.sql.SQLException;

public interface AdminDAO {


    void addAdmin(ActionEvent event, Admin a);

    void Seconnecter(ActionEvent event , String username , String password);
   void Envoyer(ActionEvent event , String tel , String codeConfidentiel );
   void UpdatePwd(ActionEvent event, String mdp1 , String tel );



}
