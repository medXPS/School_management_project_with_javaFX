package com.example.gestionecolesup;

import com.example.gestionecolesup.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    public static String[] str= null;
    public String [] getStr(){
        return this.str;
    }
    @FXML
    private Label nomComplete;
    @FXML
    private Label nomUtilisateur;

    @FXML
    private Label tel;

    @Override
    public void initialize(URL location , ResourceBundle resources){
        if(str != null)
            this.setValues(str);
    }

    public  void setValues(String [] str ){
        this.str = str;
      String   _nomComplete=str[0];
      nomComplete.setText(_nomComplete);
      String _tel = str[1];
      tel.setText(_tel);
      String _nomUtilisateur=str[2];
      nomUtilisateur.setText(_nomUtilisateur);
 }
    public void Modifier_nomUtilisateur( ActionEvent event ) throws IOException {
        scene  s = new scene();
        s.changeScene(event,"ProfileChangeUSERNAME.fxml" ,  "\n" +
                "Profile ", 960 , 640);
    }
    public void Modifier_tel( ActionEvent event ) throws IOException {
        scene  s = new scene();
        s.changeScene(event,"ProfileChangeTEL.fxml" ,  "\n" +
                "Profile ", 960 , 640);
    }

    public void Modifier_MotDePass( ActionEvent event ) throws IOException {
        scene  s = new scene();
        s.changeScene(event,"ProfileChangePSW.fxml" ,  "\n" +
                "Profile ", 960 , 640);
    }

    public void Retour_DashBoard(ActionEvent event ) throws IOException {
        scene  s = new scene();
        s.changeScene(event,"Dashboard.fxml" ,  "\n" +
                "Gestion d 'Ecole", 960 , 640);
        DashBoradController d= s.getLoader().getController() ;
        d.KeepChanges(str);

    }

    }

