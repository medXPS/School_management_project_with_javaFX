package com.example.gestionecolesup.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormationDaoImpl implements Formationdao{
// L'enrigestrement des formations
    @Override
    public void saveFormation(Formation f) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }

        // Update
        String query = "insert into formation (object, date, description, formateur)  VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, f.getObject());
            preparedStatement.setString(2, f.getDate());
            preparedStatement.setString(3, f.getFormateur());
            preparedStatement.setString(4, f.getDescription());



            preparedStatement.executeUpdate();
            System.out.println("Saved");
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
//affichage des formations dans la table des formations
    @Override
    public ObservableList<Formation> ShowListFormation() {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con==null){
            return null;

        }
        ObservableList<Formation> formations = FXCollections.observableArrayList();

        String query="Select * from formation";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                formations.add(new Formation(resultSet.getString("object"),resultSet.getString("date"),resultSet.getString("formateur"),resultSet.getString("description")));


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
        return formations;
    }
    }

