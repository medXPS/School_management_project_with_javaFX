package com.example.gestionecolesup.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CongeDaoImpl implements Congedao{
//   cett methode permet l'enrigestrement des conges
    @Override
    public void saveconge(Conge c) {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con == null) {
            return;
        }

        // Update
        String query = "insert into conge (name, date, cin, nbrdejrs)  VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, c.getNameemlpoye());
            preparedStatement.setString(2, c.getDatedebutconge());
            preparedStatement.setString(3, c.getCinemlpoye());
            preparedStatement.setString(4, c.getNbrdejrs());



            preparedStatement.executeUpdate();
            System.out.println("Congé Saved");
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


//affichage des lists de conges
    @Override
    public ObservableList<Conge> ShowListConge() {
        daoFactory conDB= new daoFactory();
        Connection con=conDB.getCon();
        if (con==null){
            return null;

        }
        ObservableList<Conge> conges = FXCollections.observableArrayList();

        String query="Select * from conge";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                conges.add(new Conge(resultSet.getString("name"),resultSet.getString("date"),resultSet.getString("cin"),resultSet.getString("nbrdejrs")));


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
        return conges;
    }
}
