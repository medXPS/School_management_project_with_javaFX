package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.profileDAO;
import com.example.gestionecolesup.dao.profileDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;


public class Modifer_Tel_Controller {
@FXML
    private TextField tel0 ;
    @FXML
    private TextField tel1 ;
    @FXML
    private TextField mdp ;
    @FXML
    public void Annuler(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"profile.fxml" ,  "\n" +
                "Profile", 960 , 640 );

}
    @FXML
    public void changerTel(ActionEvent event ){
        String _tel0  = tel0.getText();
        String _tel1 = tel1.getText() ;
        String  _mdp =  mdp.getText();
        if(tel0.getText().isBlank() == false && tel1.getText().isBlank() == false && mdp.getText().isBlank() == false){
            profileDAO profile  = new profileDAOimpl();
            profile.modifier_tel( event , _mdp , _tel0, _tel1 );

        }
        else {
            System.out.println("\n" +
                    "Vous devez entrer vos informations");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez Remplire tous les champs  ");
            alert.show();
        }


    }
}