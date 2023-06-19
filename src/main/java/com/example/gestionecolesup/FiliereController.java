package com.example.gestionecolesup;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.Filiere;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class FiliereController  {
// variable of the table in the filierw interface
    @FXML
    private TableColumn<Filiere, String> Nomdufiliere;
    @FXML
    private TableColumn<Filiere, String> IDF;
    @FXML
    private TableColumn<Filiere, String> Nom_du_Responsable;
    @FXML
    private TableColumn<Filiere, String> Niveau;
    @FXML
    private TableColumn<Filiere, String> Nombre_Etudiants;
    @FXML
    private TableColumn<Filiere, String> Nombre_Modules;
    @FXML
    private TableColumn<Filiere, String> semestre;
    @FXML
    private TableView<Filiere> tableFiliere;
    @FXML
    private Label lblNbFilieres;

    ObservableList<Filiere> listfiliere;
    FiliereDAO filieredao=new FiliereDAOimpl();

    //For getting the value from the data ase and affiche it in the screan
    public void refreshtablefiliere(){
        Nomdufiliere.setCellValueFactory(new PropertyValueFactory<Filiere,String>("nomDuFiliere"));
        Nom_du_Responsable.setCellValueFactory(new PropertyValueFactory<Filiere,String>("nomDuResponsable"));
        Niveau.setCellValueFactory(new PropertyValueFactory<Filiere,String>("niveau"));
        Nombre_Etudiants.setCellValueFactory(new PropertyValueFactory<Filiere,String>("nombreEtudiants"));
        Nombre_Modules.setCellValueFactory(new PropertyValueFactory<Filiere,String>("nombreModules"));
        semestre.setCellValueFactory(new PropertyValueFactory<Filiere,String>("Semestre"));
        IDF.setCellValueFactory(new PropertyValueFactory<Filiere,String>("id"));

        listfiliere=filieredao.ShowListFiliere();
        tableFiliere.setItems(listfiliere);

        lblNbFilieres.setText(String.valueOf(listfiliere.size()));
    }
    @FXML
    public void filierelist(ActionEvent event) throws IOException {
        refreshtablefiliere();
    }
    // deleting filiere for the table and the data base

    public void deletefiliere(){
        if (tableFiliere.getSelectionModel().getSelectedItem()==null){
            return;
        }
        daoFactory conDB = new daoFactory();

        String query = "DELETE FROM filiere WHERE Nom_du_filiere=?;";
        try (PreparedStatement preparedStatement = conDB.getCon().prepareStatement(query)) {
            preparedStatement.setString(1, tableFiliere.getSelectionModel().getSelectedItem().getNomDuFiliere());
            preparedStatement.executeUpdate();
            System.out.println("deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                conDB.getCon().close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        refreshtablefiliere();
    }

    // for the button ruteuen to service
        public void RuturnServices(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Services.fxml" ,  "\n" +
                "Services", 960 , 640 );
    }

    //for the button to go to the  add filiere
    public void ADDF(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Addfiliere.fxml" ,  "\n" +
                "ADF", 960 , 640 );
    }

    // for the button to  go the interface of updateing filiere
    public void UPDF(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"FiliereUpdatV2e.fxml" ,  "\n" +
                "UPDATE", 960 , 640 );
    }
}


