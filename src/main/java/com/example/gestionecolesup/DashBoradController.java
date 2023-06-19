package com.example.gestionecolesup;


import com.example.gestionecolesup.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoradController implements Initializable {
    public static String[] str={"","",""};
    public void initialize(URL location , ResourceBundle resources){

    }

    public static  void RetriveValues(String nc , String tel, String nu){
        str[0]=nc;
        str[1]=tel;
        str[2]=nu;
    }
    public static  void KeepChanges(String [] s){
        str=s;
    }

    public void Deconnecter(ActionEvent event) throws IOException {
        Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Deconnexion ");
        alert.setHeaderText("vous êtes sur le point de vous déconnecter");
        alert.setContentText(" \n" +
                "Cliquez sur ok pour vous déconnecter");

        scene s = new scene();
        s.changeScene(event,"Login.fxml" ,  "\n" +
                "Connexion ", 960 , 640 );
    }
    public void Services(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Services.fxml" ,  "\n" +
                "Services", 960 , 640 );
    }
    @FXML
    public void Acceder_Profile( ActionEvent event ) throws IOException {
        scene  s = new scene();
        s.changeScene(event,"profile.fxml" ,  "\n" +
                "Profile ", 960 , 640);
        profileController p=s.getLoader().getController();
        p.setValues(str);

    }

}
