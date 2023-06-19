package com.example.gestionecolesup;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class ServiceController {
    public void RuturnDashboard(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Dashboard.fxml" ,  "\n" +
                "Home", 960 , 640 );
    }
    public void Scolrite(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Scolarite.fxml" ,  "\n" +
                "Scolarite", 960 , 640 );
    }
    public void RH(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"RHhome.fxml" ,  "\n" +
                "Scolarite", 960 , 640 );
    }
    @FXML
    public void Deconnecter(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Deconnexion ");
        alert.setHeaderText("vous êtes sur le point de vous déconnecter");
        alert.setContentText(" \n" +
                "Cliquez sur ok pour vous déconnecter");

        scene s = new scene();
        s.changeScene(event,"Login.fxml" ,  "\n" +
                "Connexion ", 960 , 640 );
    }
    public void acceder_au_profile(ActionEvent event ) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Dashboard.fxml" ,  "\n" +
                "Gestion d'Ecole ", 960 , 640 );

    }

}
