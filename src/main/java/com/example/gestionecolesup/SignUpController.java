package com.example.gestionecolesup;
import com.example.gestionecolesup.dao.AdminDAO;
import com.example.gestionecolesup.dao.adminDAOimpl;
import com.example.gestionecolesup.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField nom ;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nomUtilisateur ;
    @FXML
    private TextField tel;
    @FXML
    private TextField motDePass;
    @FXML
    void addAdmin(ActionEvent event) {
        String Nom  = nom.getText();
        String Prenom  = prenom.getText();
        String NomUtilisateur  = nomUtilisateur.getText();
        String Tel = tel.getText();
        String MotDePass = motDePass.getText();
        Admin a = new Admin(Nom,Prenom , NomUtilisateur, Tel, MotDePass);
        AdminDAO adminDAO = new adminDAOimpl();
        if (nomUtilisateur.getText().isBlank()==false && motDePass.getText().isBlank()==false && nom.getText().isBlank()==false && prenom.getText().isBlank()==false && tel.getText().isBlank()==false&& motDePass.getText().isBlank()==false){

                 if(!containsNumbers(tel.getText())){  System.out.println("Not a number ");
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setContentText("Le champ *Telephone* doit contenir des nombres");
                     alert.show();

                 }
                 else {
                     adminDAO.addAdmin(event,a);

        }
        }
        else {
            System.out.println("\n" +
                    "vous devez entrer vos informations");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Remplissage de tous les champs est obligatoire");
            alert.show();
        }
    }
    @FXML
    public void connecter(ActionEvent event ) throws IOException {
        scene s= new scene();
        s.changeScene( event,"Login.fxml" ,  "Se connecter", 960 , 640  );
    }

    public static boolean containsNumbers(String string) {
        for (int i = 0; i < string.length(); ++i) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}








