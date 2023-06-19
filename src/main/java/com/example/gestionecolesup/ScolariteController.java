package com.example.gestionecolesup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class ScolariteController {
    public void RuturnServices(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Services.fxml" ,  "\n" +
                "Services", 960 , 640 );
    }
    public void Deconnecter(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Deconnexion ");
        alert.setHeaderText("Vous êtes sur le point de vous déconnecter");
        alert.setContentText(" \n" +
                "Cliquez sur ok pour vous déconnecter");

        scene s = new scene();
        s.changeScene(event,"Login.fxml" ,  "\n" +
                "Connexion ", 960 , 640 );
    }

    public void showEtudiants(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Etudiant.fxml" ,  "\n" +
                "Etudiants", 960 , 640 );
    }
    @FXML
    public void showCours(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Cours.fxml" ,  "\n" +
                "Cours", 960 , 640 );
    }


    public void changeTOfilieree(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Filiere.fxml" ,  "\n" +
                "Filiere", 960 , 640 );
    }
}
