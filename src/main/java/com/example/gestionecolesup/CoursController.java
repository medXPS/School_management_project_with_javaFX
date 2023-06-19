package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.daoFactory;
import com.example.gestionecolesup.model.Cours;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import com.example.gestionecolesup.dao.*;

import javafx.scene.control.ChoiceBox;



    public class CoursController implements Initializable {

        @FXML
        private TableColumn<Cours, String> nomt;
        @FXML
        private TableColumn<Cours, String> semestret;
        @FXML
        private TableColumn<Cours, String> professeurt;
        @FXML
        private TextField nom;
        @FXML
        private TextField semestre;
        @FXML
        private TextField professeur;
        @FXML
        private TextField Nom;
        @FXML
        private TextField Semestre;
        @FXML
        private TextField Professeur;

        @FXML
        private TableView<Cours> tablecours;
        @FXML
        private ChoiceBox<String> niveau ;
        @FXML
        private ChoiceBox<String>  filiere  ;
        ObservableList<Cours> Cours;
        CoursDAO Coursdao = new coursDAOimpl();


        private  String [] nv={"CP1","CP2","CI1","CI2","CI3"};
        private  String [] fl={"GI","GE","RSSP","GCDSE","GIL"};

        public void initialize(URL arg0, ResourceBundle arg1) {

            if(niveau.getItems().isEmpty()) {
                niveau.getItems().addAll(nv);

            }
            if(filiere.getItems().isEmpty()) {
                filiere.getItems().addAll(fl);

            }
            tablecours.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cours>() {
                @Override
                public void changed(ObservableValue<? extends Cours> observable, Cours oldValue, Cours newValue) {
                    Cours selectedCours = tablecours.getSelectionModel().getSelectedItem();
                    String noms = selectedCours.getNom();
                    String semestres = selectedCours.getSemestre();
                    String professeurs = selectedCours.getProfesseur();
                    Nom.setText(noms);
                    Semestre.setText(semestres);
                    Professeur.setText(professeurs);

                }
            });

        }
        @FXML
        public void refreshtablecours() {
            String Filiere = filiere.getSelectionModel().getSelectedItem();
            String Niveau = niveau.getSelectionModel().getSelectedItem();

            nomt.setCellValueFactory(new PropertyValueFactory<Cours,String>("nom"));
            semestret.setCellValueFactory(new PropertyValueFactory<Cours,String>("semestre"));
            professeurt.setCellValueFactory(new PropertyValueFactory<Cours,String>("professeur"));


            Cours = Coursdao.Showcours(Filiere,Niveau);
            tablecours.setItems(Cours);
        }

        @FXML
        public void ShowlistCours() {
            this.refreshtablecours();
        }
        @FXML
        void addcours(ActionEvent event) {
            String Filiere = filiere.getSelectionModel().getSelectedItem();
            String Niveau = niveau.getSelectionModel().getSelectedItem();

            String Nom = nom.getText();
            String Semestre = semestre.getText();
            String Professeur = professeur.getText();
            if (Nom.isEmpty() || Semestre.isEmpty() || Professeur.isEmpty()  ){
                System.out.println("Tous les champs doit etre remplies");
            } else {

                CoursDAO coursdao = new coursDAOimpl();
                Cours c = new Cours(Nom, Semestre, Professeur);
                coursdao.savecours(c,Filiere,Niveau);
                nom.clear();
                semestre.clear();
                professeur.clear();

                System.out.println("cours ajouté avec succès");

            }
            refreshtablecours();
        }
        @FXML
        void deletecours(ActionEvent event) {
            if (tablecours.getSelectionModel().getSelectedItem()==null){
                return;
            }
            daoFactory conDB= new daoFactory();
            Connection con=conDB.getCon();
            String query = "DELETE FROM cours WHERE nom=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

                preparedStatement.setString(1, tablecours.getSelectionModel().getSelectedItem().getNom());


                preparedStatement.executeUpdate();
                refreshtablecours();
                System.out.println("deleted");
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            refreshtablecours();

        }


        public void updatecours(ActionEvent event) {
            String Filiere = filiere.getSelectionModel().getSelectedItem();
            String Niveau = niveau.getSelectionModel().getSelectedItem();

            String nomu = Nom.getText();
            String semestreu = Semestre.getText();
            String professeuru = Professeur.getText();


            // Update the student in the database with the new values
            CoursDAO coursdao = new coursDAOimpl();
            coursdao.updatecours(nomu, semestreu, professeuru,Filiere,Niveau);

            // Refresh the table to show the updated student
            Nom.clear();
            Semestre.clear();
            Professeur.clear();
            refreshtablecours();
        }

        public void returnScolarite(ActionEvent event) throws IOException {
            scene s = new scene();
            s.changeScene(event,"Scolarite.fxml" ,  "\n" +
                    "Scolarite", 960 , 640 );
        }

    }


