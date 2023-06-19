package com.example.gestionecolesup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class daoFactory{
        //identifier votre base de donnes , nom d'utilisateur et mot de passe
        private static Connection con;

        public static Connection getCon() {
            return con;
        }

        public void setCon(Connection con) {
            this.con = con;
        }

        public daoFactory() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/gestion_ecole";
                String  username="root";
                String  password = "ENSA2023";
                con = DriverManager.getConnection(url,username,password);
                System.out.println("Mysql Server Connected Successfully !");
            }catch (ClassNotFoundException n){
                n.printStackTrace();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

