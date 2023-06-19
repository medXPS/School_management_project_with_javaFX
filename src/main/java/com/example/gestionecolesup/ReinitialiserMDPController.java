package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.AdminDAO;
import com.example.gestionecolesup.dao.adminDAOimpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ReinitialiserMDPController {
    public  TextField  tel ;
    public  TextField codeConfidentiel ;


        public void Envoyer(ActionEvent event ) {
            String Tel= tel.getText();
            String CodeConfidentiel = codeConfidentiel.getText();
            if (tel.getText().isBlank()==false  && codeConfidentiel.getText().isBlank()==false){
                AdminDAO ReinitialiserAdmin = new adminDAOimpl();
                ReinitialiserAdmin .Envoyer(event,Tel,CodeConfidentiel );



            }
            else {
                System.out.println("\n" +
                        "Vous devez entrer vos informations");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Vous devez remplir tous les champs ");
                alert.show();
            }
        }
    public void connecter(ActionEvent event ) throws IOException {
        scene s= new scene();
        s.changeScene( event,"Login.fxml" ,  "Se connecter", 960 , 640  );
    }


}

