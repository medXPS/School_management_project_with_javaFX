package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.FiliereDAO;
import com.example.gestionecolesup.dao.FiliereDAOimpl;
import com.example.gestionecolesup.dao.daoFactory;
import com.example.gestionecolesup.model.Cours;
import com.example.gestionecolesup.model.Filiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatFiliereController
{

    // variables of formaulaire update
    @FXML
    private TextField Nomfiliere;
    @FXML
    private TextField ID;
    @FXML
    private TextField NomduResponsable;
    @FXML
    private TextField NombreEtudiant;

    @FXML
    private TextField Semestree;
    @FXML
    private TextField Niveauu;
    @FXML
    private TextField NombreModuless;




   @FXML
    public void  GetData(ActionEvent event) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
       if (con == null) {
           return;
       }
       String id = ID.getText();


        String query="Select * FROM filiere WHERE id=?";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, id);


            ResultSet resultset=preparedStatement.executeQuery();
            while (resultset.next()){
                String NF = resultset.getString("Nom_du_filiere");
                Nomfiliere.setText(NF);
                String NR = resultset.getString("Nom_du_Responsable");
                NomduResponsable.setText(NR);
                String N = resultset.getString("Niveau");
                Niveauu.setText(N);
                String NE = resultset.getString("Nombre_Etudiants");
                NombreEtudiant.setText(NE);
                String NM = resultset.getString("Nombre_Modules");
                NombreModuless.setText(NM);
                String S = resultset.getString("semestre");
                Semestree.setText(S);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try{
                con.close();

            }catch (SQLException se){
                se.printStackTrace();
            }
        }

    }



    @FXML
    public void UpdateFiliere(ActionEvent event) throws IOException {

        String id = ID.getText();
        String nomDuFilieree = Nomfiliere.getText();
        String nomDuResponsablee = NomduResponsable.getText();
        String niveauu = Niveauu.getText();
        String nombreEtudiantss = NombreEtudiant.getText();
        String nombreModuless = NombreModuless.getText();
        String semestreee = Semestree.getText();

        FiliereDAO filiereDAO = new FiliereDAOimpl();
            filiereDAO.UpdateFiliere(id, nomDuFilieree, nomDuResponsablee, niveauu, nombreEtudiantss, nombreModuless, semestreee);
            scene s = new scene();
            s.changeScene(event,"Filiere.fxml" ,  "\n" +
                    "Filiere", 960 , 640 );

    }




    // for going back to Fileire this is for the UPDATING RETURN TO FILERE
    public void Bacck(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Filiere.fxml" ,  "\n" +
                "Filiere", 960 , 640 );
    }


}
