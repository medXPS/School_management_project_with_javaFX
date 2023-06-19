package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.model.Cours;
import com.example.gestionecolesup.model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class coursDAOimpl implements CoursDAO{
    @Override
    public void savecours(Cours c,String filiere,String niveau) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }

        // Update
        String query = "insert into cours (filiere,niveau, nom, semestre, professeur)  VALUES ( ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, filiere);
            preparedStatement.setString(2, niveau);
            preparedStatement.setString(3, c.getNom());
            preparedStatement.setString(4, c.getSemestre());
            preparedStatement.setString(5, c.getProfesseur());


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

    @Override
    public void updatecours(String nom, String semestre, String professeur,String filiere, String niveau) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }
        // Update
        String query = "UPDATE cours SET filiere=?,niveau=?,semestre=?, professeur=? WHERE nom=? ;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, filiere);
            preparedStatement.setString(2, niveau);
            preparedStatement.setString(3, semestre);
            preparedStatement.setString(4, professeur);
            preparedStatement.setString(5, nom);

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

    @Override
    public ObservableList<Cours> Showcours(String filiere, String niveau) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con==null){
            return null;

        }
        ObservableList<Cours> cours = FXCollections.observableArrayList();

        String query="Select * FROM cours WHERE filiere =? AND niveau=?";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, filiere);
            preparedStatement.setString(2, niveau);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                cours.add(new Cours(resultSet.getString("nom"),resultSet.getString("semestre"),resultSet.getString("professeur")));

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
        return cours;
    }


}
