package com.example.gestionecolesup;

import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.Formation;
import com.example.gestionecolesup.model.Recrutement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RHController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField apply_for;
    @FXML
    private Label labelmsg;
    @FXML
    private ChoiceBox<String> genre;
    private String[] Genre = {"Homme","Femme"};
    @FXML
    private TextField object;
    @FXML
    private TextField formateur;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date;
    @FXML
    private Label label;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
   if(genre.getItems().isEmpty())
       genre.getItems().addAll(Genre);
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

        public static boolean isValidNumb(String s)
        {
            Pattern p = Pattern.compile("^\\d{10}$");
            Matcher m = p.matcher(s);
            return (m.matches());
        }
    public static boolean checkdouble(String email){
        boolean check=true;
        String x="";
        ResultSet resultSet;
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        String query="Select * from recrutement where email =? ";
        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, email);
             resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                if (resultSet.getString("email").equals(email)){
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
    public void back(ActionEvent event) throws IOException {

scene s = new scene();
        s.changeScene(event,"recrutement.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );

    }
    public void backtoformation(ActionEvent event) throws IOException {
        scene s = new scene();
        s.changeScene(event,"Formations.fxml" ,  "\n" +
                "Ressources humaines", 960 , 640 );
    }
    @FXML
    protected void addCondi(ActionEvent event) throws IOException {
        String Genre = genre.getValue();
        String namee = name.getText();
        String cine = cin.getText();
        String emaile = email.getText();
        String phonee = phone.getText();
        String apply_fore = apply_for.getText();
        String s=null;
        checkdouble(emaile);
        if (namee.isEmpty() || cine.isEmpty() || emaile.isEmpty() || phonee.isEmpty() || apply_fore.isEmpty() ){
            s="\n" +
                    "Tous les champs doit être remplies\n";
            labelmsg.setText(s) ;
        } else {

            if (isValid(emaile) && isValidNumb(phonee) ) {
               if (checkdouble(emaile)){
                    Recrutementdao recrutementdao = new RecrutementDaoImpl();
                    Recrutement re = new Recrutement(namee, cine, emaile, phonee, Genre , apply_fore);
                    recrutementdao.save(re);
                    name.clear();
                    cin.clear();
                    email.clear();
                    phone.clear();
                    apply_for.clear();

                   s= "Le recrutement a été ajouté avec succès";
                   labelmsg.setText(s) ;
                }else  {


                   s="\n" +
                           "Adresse e-Mail déjà utilisée";
                   labelmsg.setText(s) ;
                }

            }else {

                s="Le format de l'e-mail ou du numéro de téléphone est incorrect\n ";
                labelmsg.setText(s) ;


            }

        }

    }
    @FXML
    public void addformation(ActionEvent event){
        String objecte = object.getText();
        String  datee = date.getValue().toString();
        String formateure = formateur.getText();
        String descriptione = description.getText();
        if (objecte.isEmpty() || datee.isEmpty() || formateure.isEmpty() || descriptione.isEmpty() ){
            label.setText("Tous les champs doit etre remplies");
        } else {
                    Formationdao formationdao = new FormationDaoImpl();
                    Formation f = new Formation(objecte,datee,formateure,descriptione);
                    formationdao.saveFormation(f);
                    object.clear();
                    formateur.clear();
                    description.clear();

                    label.setText("La formation a été ajoutée avec succès");

        }

    }



}