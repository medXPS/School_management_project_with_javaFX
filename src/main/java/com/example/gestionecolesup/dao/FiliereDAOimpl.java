package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Alert;

import java.sql.*;


public class FiliereDAOimpl implements FiliereDAO {
// enrigestrement des filiers
 @Override
public void saveFiliere(Filiere f) {
     daoFactory conDB = new daoFactory();
     boolean success=false ;
     PreparedStatement stmt=null;
     ResultSet resultSet=null;
    if (conDB.getCon() == null) {
        return;
    }

    // Update
    String query = "insert into filiere (Nom_du_filiere, Nom_du_Responsable, Niveau, Nombre_Etudiants,Nombre_Modules,semestre)  VALUES (?, ?, ?, ?,?,?);";
    try (PreparedStatement preparedStatement = conDB.getCon().prepareStatement(query)) {

        preparedStatement.setString(1, f.getNomDuFiliere());
        preparedStatement.setString(2, f.getNomDuResponsable());
        preparedStatement.setString(3, f.getNiveau());
        preparedStatement.setString(4, f.getNombreEtudiants());
        preparedStatement.setString(5, f.getNombreModules());
        preparedStatement.setString(6, f.getSemestre());
        preparedStatement.executeUpdate();
        System.out.println("success!");
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Added success !");
        alert.show();
        System.out.println("Saved");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            conDB.getCon().close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

//affichage des filiers
    @Override
    public ObservableList<Filiere> ShowListFiliere() {
        daoFactory conDB = new daoFactory();
        Statement stmt;
        ResultSet resultat = null;
        if (conDB.getCon() ==null){
            return null;
        }
        ObservableList<Filiere> filiere = FXCollections.observableArrayList();
        String query="Select * from filiere";
        try(PreparedStatement preparedStatement= conDB.getCon().prepareStatement(query)){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                filiere.add(new Filiere(resultSet.getString("ID"),resultSet.getString("Nom_du_filiere"),resultSet.getString("Nom_du_Responsable"),resultSet.getString("Niveau"),resultSet.getString("Nombre_Etudiants"),resultSet.getString("Nombre_Modules"),resultSet.getString("semestre")));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try{
                conDB.getCon().close();

            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return  filiere;
    }
// modification des filiers
    @Override
    public void UpdateFiliere(String id, String nomDuFiliere, String nomDuResponsable, String niveau, String nombreEtudiants, String nombreModules, String Semestre) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }
        // Update
        String query = "UPDATE filiere SET Nom_du_filiere=?,Nom_du_Responsable=?,Niveau=?, Nombre_Etudiants=?,Nombre_Modules=?,semestre=? WHERE id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, nomDuFiliere);
            preparedStatement.setString(2,nomDuResponsable );
            preparedStatement.setString(3, niveau);
            preparedStatement.setString(4, nombreEtudiants);
            preparedStatement.setString(5, nombreModules);
            preparedStatement.setString(6, Semestre);
            preparedStatement.setString(7, id);
            preparedStatement.executeUpdate();
            System.out.println("Saved");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(" Filiere Modified suuui");
            alert.show();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    }

