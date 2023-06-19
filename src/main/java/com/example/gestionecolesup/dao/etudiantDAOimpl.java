package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;


public class etudiantDAOimpl implements EtudiantDAO {


    @Override
    public void saveetudiant(Etudiant e,String filiere,String niveau) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }

        // Update
        String query = "insert into etudiants (filiere,niveau, nom, prenom, cne, tel,date_naissance)  VALUES ( ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, filiere);
            preparedStatement.setString(2, niveau);
            preparedStatement.setString(3, e.getNom());
            preparedStatement.setString(4, e.getPrenom());
            preparedStatement.setString(5, e.getCne());
            preparedStatement.setString(6, e.getTel());
            preparedStatement.setString(7, e.getDate_naissance());


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
    public void updateEtudiant(String nom, String prenom, String cne, String tel, LocalDate dateNaissance, String filiere, String niveau) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }
        // Update
        String query = "UPDATE etudiants SET filiere=?,niveau=?,nom=?, prenom=?,tel=?,date_naissance=? WHERE cne=? ;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, filiere);
            preparedStatement.setString(2, niveau);
            preparedStatement.setString(3, nom);
            preparedStatement.setString(4, prenom);
            preparedStatement.setString(5, tel);
            preparedStatement.setString(6, String.valueOf(dateNaissance));
            preparedStatement.setString(7, cne);

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
    public ObservableList<Etudiant> ShowEtudiant(String filiere, String niveau) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con==null){
            return null;

        }
        ObservableList<Etudiant> etudiant = FXCollections.observableArrayList();

        String query="Select nom, prenom, cne, tel, date_naissance FROM etudiants WHERE filiere =? AND niveau=?";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, filiere);
            preparedStatement.setString(2, niveau);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                etudiant.add(new Etudiant(resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("cne"),resultSet.getString("tel"),resultSet.getString("date_naissance")));

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
        return etudiant;
    }


}



