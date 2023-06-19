package com.example.gestionecolesup;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RH_ListsController  {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableColumn<Recrutement, String> apply_for;

    @FXML
    private TableColumn<Recrutement, String> cin;

    @FXML
    private TableColumn<Recrutement, String> email;

    @FXML
    private TableColumn<Recrutement, String> gender;

    @FXML
    private TableColumn<Recrutement, String> name;

    @FXML
    private TableColumn<Recrutement, String> phone;

    @FXML
    private TableView<Recrutement> table;
    @FXML
    private TableColumn<Formation, String> description;

    @FXML
    private TableColumn<Formation,String> formateur;
    @FXML
    private TableColumn<Formation,String> date;

    @FXML
    private TableColumn<Formation, String> object;

    @FXML
    private TableView<Formation> tableFormation;
    @FXML
    private HBox hBoxcard;

    private List<Card> recently;
    @FXML
    private TextField cinEmploye;

    @FXML
    private DatePicker datedebutconge;

    @FXML
    private TextField nameEmploye;

    @FXML
    private TextField nbrdejrs;
    @FXML
    private Label labelconge;
    @FXML
    private Label lblNbFormation;
    @FXML
    private TableColumn<Conge, String> cinc;

    @FXML
    private TableColumn<Conge,String> datec;

    @FXML
    private TableColumn<Conge, String> namec;

    @FXML
    private TableColumn<Conge, String> nbrdejrsc;

    @FXML
    private TableView<Conge> tableconge;
    @FXML
    private Label lblNbConge;

    public static boolean isValidNumb(String s)
    {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    public static boolean checkdouble(String name,String date){
        boolean check=true;
        String x="";
        ResultSet resultSet;
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        String query="Select * from conge where name =? and date =? ";
        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                if (resultSet.getString("name").equals(name) && resultSet.getString("date").equals(date)  ){
                    check=false;
                }


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
        return check;
    }

    @FXML
    void addconge(ActionEvent event) {
        String namee = nameEmploye.getText();
        String datee = datedebutconge.getValue().toString();
        String cinee = cinEmploye.getText();
        String nbrdejrse = nbrdejrs.getText();
        if (namee.isEmpty() || datee.isEmpty() || cinee.isEmpty() || nbrdejrse.isEmpty() ){
            labelconge.setText("Tous les champs doit etre remplies");
        } else {
            if (isValidNumb(nbrdejrse )) {
                if (checkdouble(namee,datee)){
                    Congedao congedao = new CongeDaoImpl();
                    Conge c = new Conge(namee, datee, cinee, nbrdejrse);
                    congedao.saveconge(c);
                    nameEmploye.clear();
                    cinEmploye.clear();
                    nbrdejrs.clear();
                    labelconge.setText("\n" +
                            "Congé a été ajouté avec succès");;

                }else {
                    labelconge.setText("Congé existe déjà");
                }
            }else {
                labelconge.setText("\n" +
                        "Format nombre de jours invalide ");
            }

        }
    }

    ObservableList<Conge> conge;

    Congedao congedao=new CongeDaoImpl();
    @FXML
    public void refreshtableconge(){
        namec.setCellValueFactory(new PropertyValueFactory<Conge,String>("nameemlpoye"));
        cinc.setCellValueFactory(new PropertyValueFactory<Conge,String>("cinemlpoye"));
        datec.setCellValueFactory(new PropertyValueFactory<Conge,String>("datedebutconge"));
        nbrdejrsc.setCellValueFactory(new PropertyValueFactory<Conge,String>("nbrdejrs"));
        conge=congedao.ShowListConge();
        tableconge.setItems(conge);
        tableconge.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lblNbConge.setText(String.valueOf(conge.size()));
    }
    @FXML
    public void ShowlistConge(){
        refreshtableconge();

    }
    @FXML
    void deleteconge(ActionEvent event) {
        if (tableconge.getSelectionModel().getSelectedItem()==null){
            return;
        }
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        String query = "DELETE FROM conge WHERE cin=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, tableconge.getSelectionModel().getSelectedItem().getCinemlpoye());


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
        refreshtableconge();

    }

    @FXML
    public void addcondipage(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Ajoutcondidat.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    public void backtorh(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"RhHome.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );

    }
    @FXML
    public void backtoservice(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Services.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );

    }
    @FXML
    public void BachToRhhome(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"RhHome.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    public void GoRecrutement(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"recrutement.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    public void GoToConge(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Congé.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    public void goToaddconge(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"AjouterUnCongé.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    public void backcongehome(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Congé.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    public void goforumformation(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"AjouterUneFormation.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );

    }
    @FXML
    public void goformation(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("Formations.fxml"));
        scene s = new scene();
        s.changeScene(event,"Formations.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }

    ObservableList<Recrutement> l;

    Recrutementdao recrutementdao=new RecrutementDaoImpl();
    @FXML
    public void refreshtablecondidat(){
        name.setCellValueFactory(new PropertyValueFactory<Recrutement,String>("name"));
        cin.setCellValueFactory(new PropertyValueFactory<Recrutement,String>("cin"));
        email.setCellValueFactory(new PropertyValueFactory<Recrutement,String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Recrutement,String>("phone"));
        gender.setCellValueFactory(new PropertyValueFactory<Recrutement,String>("gender"));
        apply_for.setCellValueFactory(new PropertyValueFactory<Recrutement,String>("apply_for"));
        l=recrutementdao.ShowListDemande();
        System.out.println(l);
        table.setItems(l);;
    }
    @FXML
    public void handle(ActionEvent event) throws IOException {
        refreshtablecondidat();
    }
    ObservableList<Formation> listformation;

    Formationdao formationdao=new FormationDaoImpl();
    @FXML
    public void refreshtableformation(){
        object.setCellValueFactory(new PropertyValueFactory<Formation,String>("object"));
        date.setCellValueFactory(new PropertyValueFactory<Formation,String>("date"));
        formateur.setCellValueFactory(new PropertyValueFactory<Formation,String>("formateur"));
        description.setCellValueFactory(new PropertyValueFactory<Formation,String>("description"));

        listformation=formationdao.ShowListFormation();
        int nbr=listformation.size();
        lblNbFormation.setText(String.valueOf(nbr));
        tableFormation.setItems(listformation);
    }
    @FXML
    public void formationlist(ActionEvent event) throws IOException {
        refreshtableformation();
    }
    @FXML
    public void deletecondidat(){
        if (table.getSelectionModel().getSelectedItem()==null){
            return;
        }
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        String query = "DELETE FROM recrutement WHERE email=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, table.getSelectionModel().getSelectedItem().getEmail());


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
        refreshtablecondidat();

    }
    @FXML
    public void deleteformation(){
        if (tableFormation.getSelectionModel().getSelectedItem()==null){
            return;
        }
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        String query = "DELETE FROM formation WHERE object=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, tableFormation.getSelectionModel().getSelectedItem().getObject());


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
        refreshtableformation();

    }


}
