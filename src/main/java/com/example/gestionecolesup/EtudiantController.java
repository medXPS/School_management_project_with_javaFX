package com.example.gestionecolesup;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.Etudiant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import java.net.URL;
import java.util.ResourceBundle;
;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;




import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class EtudiantController implements  Initializable{

    @FXML
    private TableColumn<Etudiant, String> nomt;
    @FXML
    private TableColumn<Etudiant, String> prenomt;
    @FXML
    private TableColumn<Etudiant, String> cnet;
    @FXML
    private TableColumn<Etudiant, String> telt;
    @FXML
    private TableColumn<Etudiant, String> date_naissancet;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cne;
    @FXML
    private TextField tel;
    @FXML
    private DatePicker date_naissance;

    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Cne;
    @FXML
    private TextField Tel;

    @FXML
    private DatePicker Date_naissance;

    @FXML
    private Label labeletudiant;

    @FXML
    private TableView<Etudiant> tableetudiant;
    @FXML
    private ChoiceBox<String> niveau ;
    @FXML
    private ChoiceBox<String>  filiere  ;
    ObservableList<Etudiant> Etudiant;
    EtudiantDAO Etudiantdao = new etudiantDAOimpl();


    private  String [] nv={"CP1","CP2","CI1","CI2","CI3"};
    private  String [] fl={"GI","GE","RSSP","GCDSE","GIL"};

    public void initialize(URL arg0, ResourceBundle arg1) {

        if(niveau.getItems().isEmpty()) {
           niveau.getItems().addAll(nv);

        }
        if(filiere.getItems().isEmpty()) {
            filiere.getItems().addAll(fl);

        }
        tableetudiant.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Etudiant>() {
            @Override
            public void changed(ObservableValue<? extends Etudiant> observable, Etudiant oldValue, Etudiant newValue) {
                Etudiant selectedEtudiant = tableetudiant.getSelectionModel().getSelectedItem();

                if(selectedEtudiant == null)
                    return;

                String noms = selectedEtudiant.getNom();
                String prenoms = selectedEtudiant.getPrenom();
                String cnes = selectedEtudiant.getCne();
                String tels = selectedEtudiant.getTel();
                String dateNaissances = selectedEtudiant.getDate_naissance();
                Nom.setText(noms);
                Prenom.setText(prenoms);
                Cne.setText(cnes);
                Tel.setText(tels);
                Date_naissance.setValue(LocalDate.parse(dateNaissances));

            }
        });

    }

    @FXML
    public void refreshtableetudiant() {
        String Filiere = filiere.getSelectionModel().getSelectedItem();
        String Niveau = niveau.getSelectionModel().getSelectedItem();

        nomt.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
        prenomt.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
        cnet.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("cne"));
        telt.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("tel"));
        date_naissancet.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("date_naissance"));

        Etudiant = Etudiantdao.ShowEtudiant(Filiere,Niveau);
        tableetudiant.setItems(Etudiant);
    }

    @FXML
    public void ShowlistEtudiant() {
        this.refreshtableetudiant();
    }
    @FXML
    void addetudiant(ActionEvent event) {
        String Filiere = filiere.getSelectionModel().getSelectedItem();
        String Niveau = niveau.getSelectionModel().getSelectedItem();

        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Cne = cne.getText();
        String Tel = tel.getText();
        String Date = date_naissance.getValue().toString();
        if (Nom.isEmpty() || Prenom.isEmpty() || Cne.isEmpty() || Tel.isEmpty() || Date.isEmpty() ){
            System.out.println("Tous les champs doit etre remplies");
        } else {

                    EtudiantDAO etudiantdao = new etudiantDAOimpl();
                    Etudiant e = new Etudiant(Nom, Prenom, Cne, Tel, Date);
                    etudiantdao.saveetudiant(e,Filiere,Niveau);
                    nom.clear();
                    prenom.clear();
                    cne.clear();
                    tel.clear();
                    System.out.println("Etudiant ajouté avec succès");

            }
        refreshtableetudiant();
        }
    @FXML
    void deleteetudiant(ActionEvent event) {
        if (tableetudiant.getSelectionModel().getSelectedItem()==null){
            return;
        }
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        String query = "DELETE FROM etudiants WHERE cne=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, tableetudiant.getSelectionModel().getSelectedItem().getCne());


            preparedStatement.executeUpdate();
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
        refreshtableetudiant();

    }


    public void updateetudiant(ActionEvent event) {
        String Filiere = filiere.getSelectionModel().getSelectedItem();
        String Niveau = niveau.getSelectionModel().getSelectedItem();

                String nomu = Nom.getText();
                String prenomu = Prenom.getText();
                String cneu = Cne.getText();
                String telu = Tel.getText();
                LocalDate dateNaissance = Date_naissance.getValue();

                // Update the student in the database with the new values
                EtudiantDAO etudiantdao = new etudiantDAOimpl();
                etudiantdao.updateEtudiant(nomu, prenomu, cneu, telu, dateNaissance,Filiere,Niveau);

                // Refresh the table to show the updated student
                Nom.clear();
                Prenom.clear();
                Cne.clear();
                Tel.clear();
                refreshtableetudiant();
            }

    public void returnScolarite(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Scolarite.fxml" ,  "\n" +
                "Scolarite", 960 , 640 );
    }

}

