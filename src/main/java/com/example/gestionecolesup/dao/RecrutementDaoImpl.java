package com.example.gestionecolesup.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;

import java.sql.*;

public class RecrutementDaoImpl implements Recrutementdao {
// permet d'enrigestrer les demandes de recrutement
    @Override
    public void save(Recrutement r) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }

         // Update
            String query = "insert into recrutement (name, cin, email, phone,gender,apply_for)  VALUES (?, ?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

                preparedStatement.setString(1, r.getName());
                preparedStatement.setString(2, r.getCin());
                preparedStatement.setString(3, r.getEmail());
                preparedStatement.setString(4, r.getPhone());
                preparedStatement.setString(5, r.getGender());
                preparedStatement.setString(6, r.getApply_for());


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

// affichage des demandes de recutement
    public ObservableList<Recrutement> ShowListDemande() {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con==null){
            return null;

        }
        ObservableList<Recrutement> recrutements = FXCollections.observableArrayList();

        String query="Select * from recrutement";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                recrutements.add(new Recrutement(resultSet.getString("name"),resultSet.getString("cin"),resultSet.getString("email"),resultSet.getString("phone"),resultSet.getString("gender"),resultSet.getString("apply_for")));


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
        return recrutements;
    }
    }

