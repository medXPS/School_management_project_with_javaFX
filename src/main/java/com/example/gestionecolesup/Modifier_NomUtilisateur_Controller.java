package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.profileDAO;
import com.example.gestionecolesup.dao.profileDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Modifier_NomUtilisateur_Controller {
    @FXML
    private TextField nomUtilisateur ;
    @FXML
    private  TextField mdp ;
    @FXML
    public void Annuler(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"profile.fxml" ,  "\n" +
                "Profile", 960 , 640 );
    }
    @FXML
    public void ChangerNomUtilisateur( ActionEvent event){
        String _nomUtilisateur= nomUtilisateur .getText();
        String _mdp = mdp.getText();
        profileController p=new profileController();

        if (mdp.getText().isBlank() == false  && nomUtilisateur.getText().isBlank() == false) {
                profileDAO newUsrname= new profileDAOimpl();
            newUsrname.modifier_nomUtilisateur( event  , p.getStr()[2], _nomUtilisateur ,_mdp );

    }
        else{
            System.out.println("\n" +
                    "Vous devez entrer vos informations");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez Remplire tous les champs  ");
            alert.show();
        }

}}
