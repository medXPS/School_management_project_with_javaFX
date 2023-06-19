package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.AdminDAO;
import com.example.gestionecolesup.dao.adminDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class NouveauMdpController {
@FXML
    public TextField mdp1;
@FXML
    public TextField mdp2 ;
@FXML
public TextField tel;
    public void Valider(ActionEvent event ) {
        String Mdp1= mdp1.getText();
        String Mdp2 = mdp2.getText();
        String Tel = tel.getText();
        if (mdp1.getText().isBlank()==false  && mdp2.getText().isBlank()==false ){
            if(Mdp1.equals(Mdp2)){

            AdminDAO newMdp = new adminDAOimpl();
            newMdp.UpdatePwd(event,Mdp1,Tel);}
            else{
                System.out.println("\n" +
                        "Les mots de passe ne sont pas égaux ");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("\n" +
                            "Les mots de passe ne sont pas égaux  ");
                    alert.show();}


        }
        else {
            System.out.println("\n" +
                    "Vous devez entrer vos informations");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez Remplire tous les champs  ");
            alert.show();
        }
    }
    public String getTel(String s ){
        return s;
    }

}
