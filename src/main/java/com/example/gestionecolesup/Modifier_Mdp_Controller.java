package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.AdminDAO;
import com.example.gestionecolesup.dao.adminDAOimpl;
import com.example.gestionecolesup.dao.profileDAO;
import com.example.gestionecolesup.dao.profileDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Modifier_Mdp_Controller  {
    @FXML
    public TextField mdp1;
    @FXML
    public TextField mdp2 ;
    @FXML
    public TextField nomUtilisateur;
    @FXML
    public void Valider(ActionEvent event) {
        String Mdp1 = mdp1.getText();
        String Mdp2 = mdp2.getText();
        String nom_utilisateur = nomUtilisateur.getText();
        if (mdp1.getText().isBlank() == false && mdp2.getText().isBlank() == false && nomUtilisateur.getText().isBlank() == false) {
            if (Mdp1.equals(Mdp2)) {

                profileDAO newMdp = new profileDAOimpl();
                newMdp.modifier_mdp(event, Mdp1, nom_utilisateur);
            } else {
                System.out.println("\n" +
                        "Les mots de passe ne sont pas égaux ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("\n" +
                        "Les mots de passe ne sont pas égaux  ");
                alert.show();
            }

        } else {
            System.out.println("\n" +
                    "Vous devez entrer vos informations");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez Remplire tous les champs  ");
            alert.show();
        }
    }

    public void Annuler(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"profile.fxml" ,  "\n" +
                "Profile", 960 , 640 );
    }
}

