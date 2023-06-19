package com.example.gestionecolesup;


import com.example.gestionecolesup.dao.FiliereDAO;
import com.example.gestionecolesup.dao.FiliereDAOimpl;
import com.example.gestionecolesup.model.Filiere;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.*;




public class ADDFCONT  {
    @FXML
    private TextField Nom_du_filiere;
    @FXML
    private TextField Nom_du_Responsable;
    @FXML
    private TextField Niveau;
    @FXML
    private TextField Nombre_Etudiants;
    @FXML
    private TextField Nombre_Modules;
    @FXML
    private TextField semestre;

    @FXML
    public void addfiliere(ActionEvent event) throws IOException {
        String nomDuFiliere = Nom_du_filiere.getText();
        String nomDuResponsable = Nom_du_Responsable.getText();
        String niveau = Niveau.getText();
        String nombreEtudiants = Nombre_Etudiants.getText();
        String nombreModules = Nombre_Modules.getText();
        String Semestre = semestre.getText();
        String id = semestre.getText();
        FiliereDAO filiereDAO = new FiliereDAOimpl();
        Filiere f = new Filiere(id,nomDuFiliere,nomDuResponsable,niveau,nombreEtudiants,nombreModules,Semestre);
        if (nomDuFiliere.isEmpty() || nomDuResponsable.isEmpty() || niveau.isEmpty() || nombreEtudiants.isEmpty() || nombreModules.isEmpty() || Semestre.isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ous les champs doit etre remplies");
            alert.show();

        } else {
            filiereDAO.saveFiliere(f);
            scene s = new scene();
            s.changeScene(event,"Filiere.fxml" ,  "\n" +
                    "Filiere", 960 , 640 );
            Nom_du_filiere.clear();
            Nom_du_Responsable.clear();
            Niveau.clear();
            Nombre_Etudiants.clear();
            Nombre_Modules.clear();
            semestre.clear();
        }
    }
    public void Back(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Filiere.fxml" ,  "\n" +
                "UPDATE", 960 , 640 );
    }

}
