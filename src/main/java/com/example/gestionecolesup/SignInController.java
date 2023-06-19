package com.example.gestionecolesup;
import com.example.gestionecolesup.dao.AdminDAO;
import com.example.gestionecolesup.dao.adminDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
public class SignInController  {

    @FXML
    private TextField nomUtilisateur ;
    @FXML
    private TextField motDePass;
    @FXML


    public void Seconnecter(ActionEvent event ) {
        String NomUtilisateur  = nomUtilisateur.getText();
        String MotDePass = motDePass.getText();
        if (nomUtilisateur.getText().isBlank()==false  && motDePass.getText().isBlank()==false){
                AdminDAO adminDAO = new adminDAOimpl();
                adminDAO.Seconnecter(event,NomUtilisateur,MotDePass );


    }
        else {
            System.out.println("\n" +
                    "vous devez entrer vos informations");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez saisir un nom d utilisateur et un mot de pass ");
            alert.show();
        }
    }
@FXML
public void inscrire(ActionEvent event )throws IOException{
        scene s = new scene();
    s.changeScene( event,"SignUp.fxml" ,  "S'inscrire", 960 , 640  );

}
public void Reinitialiser(ActionEvent event) throws IOException {
        scene s = new scene() ;
        s.changeScene(event , "Reset_pass_Pre_req.fxml","Reinialiser votre mot de pass ", 960 , 640 );
}
}








