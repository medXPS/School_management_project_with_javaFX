package com.example.gestionecolesup.dao;

import com.example.gestionecolesup.profileController;
import com.example.gestionecolesup.scene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class profileDAOimpl implements  profileDAO, Initializable {

    //-------------------------------------- Le principe ici est a chaque modification la variable de session str1 change ca valeur cette variable est envoyee a la class profileController pour la mise a jours  des informations

//intialisation variable static  qui joue le role de session
    public static String[] str1={"","",""};
    public void initialize(URL location , ResourceBundle resources){

    }

// focntion qui metre a jours la variable  de session apres chaque modification
    public static  void RetriveValues1(String nc , String tel, String nu){
        str1[0]=nc;
        str1[1]=tel;
        str1[2]=nu;

    }
    // modification de numero de telephone
    public void modifier_tel( ActionEvent event, String mdp,String ancien_tel, String noveau_tel){
        daoFactory conDB =new daoFactory();
        profileDAOimpl p =new profileDAOimpl();
        String mdp0=null;
        String tel0 = null ;
        String tel1=null;
        String [] s={"","",""};
        String [] queries ={"select motDePass from admins where tel=?",
                "SELECT is_phone_number_used  FROM admin_reset  WHERE tel=? ",
                "select tel from admins where tel =?",
                "update admins set tel =? where tel=?",
                "select * from admins where tel =?",
                "update admin_reset set  is_phone_number_used =? where  tel =?"
            ,"select tel from admin_reset where tel=?"};
        PreparedStatement s0,s1,s2,s3,s4,s5,s6=null;
        ResultSet res0,res1,res2,res3,res4=null;
        try{
            s0=conDB.getCon().prepareStatement(queries[2]);
            s0.setString(1,ancien_tel );
            res0 = s0.executeQuery();
            while(res0.next()){
                 tel0=res0.getString("tel");}

                 s1=conDB.getCon().prepareStatement(queries[0]);
                 s1.setString(1,ancien_tel );
                 res1 = s1.executeQuery();
                              while(res1.next()){
                                          mdp0=res1.getString("motDePass");

                }

            //si le num tele est incorrect
            if(!ancien_tel.equals(tel0)){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("\n" +
                        "Impossible de mettre à jour le numero de telephone  : Telephone actuel est invalide  ! ");
                alert.show();}
            //si le mot de pass est incorrect
          else if(!mdp.equals(mdp0)){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("\n" +
                        "Impossible de mettre à jour le numero de telephone  : Mot de passe incorrect! ");
                alert.show();}
          else {
              s6=conDB.getCon().prepareStatement(queries[6]);
              s6.setString(1,noveau_tel);
              res4=s6.executeQuery();
              while(res4.next()){
                  tel1=res4.getString("tel");
              // tel1 ici est le num de telephone qui est stocke dans la base de donnes admin_reset avec  un indicateur boolean qui permet de savoir si le numero est utilise ou non
              }
              if(!noveau_tel.equals(tel1)){
                  System.out.println("Erreur  :Le nouveau Numero de telephone est Invalide!");
                  Alert alert=new Alert(Alert.AlertType.ERROR);
                  alert.setContentText("Le nouveau numero de telephone est Invalide!!");
                  alert.show();
              }
              else{
                s2 =conDB.getCon().prepareStatement(queries[1]);
                s2.setString(1,noveau_tel);
                res2=s2.executeQuery();
                boolean is_phone_already_set=false;
                while(res2.next()){
                    Boolean r=res2.getBoolean("is_phone_number_used");

                    if(r){
                        System.out.println("Erreur  :Numero de telephone est deja utilise");
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Numero de telephone est deja utilise  !");
                        alert.show();
                        is_phone_already_set=true;
                        break;
                    }
                }
                // le nouveau numero de telephone est valide et libre

                    if(!is_phone_already_set){
                      s3 =conDB.getCon().prepareStatement(queries[3]);
                      s3.setString(1, noveau_tel);
                      s3.setString(2,ancien_tel);
                      s3.executeUpdate();
                      s4=conDB.getCon().prepareStatement(queries[4]);
                      s4.setString(1,noveau_tel);
                       res3=s4.executeQuery();
                       while(res3.next()){
                        s[0]= res3.getString("nom")+"  "+ res3.getString("prenom");
                        s[1] = res3.getString("tel");
                        s[2]=res3.getString("nomUtilisateur");
                    }
                    s5 =conDB.getCon().prepareStatement(queries[5]);
                    s5.setBoolean(1, true);
                    s5.setString(2,noveau_tel);
                    s5.executeUpdate();
                    p. RetriveValues1(s[0],s[1],s[2]);
                    System.out.println("success  ");
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Success  ");
                    alert.show();
                    scene sc = new scene();
                    sc.changeScene(event,"profile.fxml" ,  "\n" +
                            "Profile", 960 , 640 );
                    // mis a jours des donnes dans l'interface de profile
                    profileController pp= sc.getLoader().getController() ;
                    pp.setValues( str1);



            }
          }}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void modifier_mdp( ActionEvent event, String mdp, String nomUtilisateur ){
        daoFactory conDB = new daoFactory();
        profileDAOimpl p = new profileDAOimpl();
        String nc=null;
        String tl=null;
        String nu = null;
        PreparedStatement _st=null;
        ResultSet _res =null;
        PreparedStatement stmt=null;
        try{
            String req="UPDATE admins "
                    + "SET motDePass =? "
                    + "WHERE nomUtilisateur =?";
            stmt =conDB.getCon().prepareStatement(req);
            stmt.setString(1,mdp);
            stmt.setString(2,nomUtilisateur );
            int res = stmt.executeUpdate();
            //si la requet sql est valide
            if (res > 0) {
                System.out.println(
                        "\n" +
                                "la table a été mise à jour avec succès.");
                System.out.println("success  ");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Success  ");
                alert.show();
                _st=conDB.getCon().prepareStatement("select * from admins where nomUtilisateur =?");
                _st.setString(1,nomUtilisateur);
                _res=_st.executeQuery();
                while(_res.next()){
                    nc= _res.getString("nom")+"  "+ _res.getString("prenom");
                    tl = _res.getString("tel");
                    nu=_res.getString("nomUtilisateur");   }
                p. RetriveValues1(nc,tl,nu);

                scene s = new scene();
                s.changeScene(event,"profile.fxml" ,  "\n" +
                        "Profile", 960 , 640 );
                //mise a jour de l interface profile
                profileController pp= s.getLoader().getController() ;
                pp.setValues( str1);
            }
            else{
                System.out.println("\n" +
                        "Impossible de mettre à jour le mot de passe");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("\n" +
                        "Impossible de mettre à jour le mot de passe : Nom d'utilisateur invalide ! ");
                alert.show();
            }
            conDB.getCon().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifier_nomUtilisateur( ActionEvent event  , String nomUtilisateur0,String nomUtilisateur1 ,String mdp ){
        daoFactory conDB = new daoFactory();
        profileDAOimpl p = new profileDAOimpl();
        String nc=null;
        String tl=null;
        String nu = null;
        String mdp0=null;
        ResultSet res1,res2 =null;
        PreparedStatement s1,s2,s3=null;

        String req1 = " select motDePass from admins where nomUtilisateur =?";
        String req2= "update admins set nomUtilisateur =? where nomUtilisateur = ?";
        String req3 ="select * from admins where nomUtilisateur =?";
        try{
            s1=conDB.getCon().prepareStatement(req1);
            s1.setString(1,nomUtilisateur0 );
             res1 = s1.executeQuery();
             while(res1.next()){
                 mdp0=res1.getString("motDePass");

             }
             if(!mdp0.equals(mdp)){
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("\n" +
                         "Impossible de mettre à jour le nom d'utilisateur : Mot de passe incorrect! ");
                 alert.show();
             }
             else {

                 s2 =conDB.getCon().prepareStatement(req2);
                 s2.setString(1,nomUtilisateur1 );
                 s2.setString(2,nomUtilisateur0);
                 s2.executeUpdate();
                 s3=conDB.getCon().prepareStatement(req3);
                 s3.setString(1,nomUtilisateur1 );
                 res2=s3.executeQuery();
                 while(res2.next()){
                     nc= res2.getString("nom")+"  "+ res2.getString("prenom");
                     tl = res2.getString("tel");
                     nu=res2.getString("nomUtilisateur");   }
                 p. RetriveValues1(nc,tl,nu);
                 System.out.println("success  ");
                 Alert alert=new Alert(Alert.AlertType.INFORMATION);
                 alert.setContentText("Success  ");
                 alert.show();
                 scene s = new scene();
                 s.changeScene(event,"profile.fxml" ,  "\n" +
                         "Profile", 960 , 640 );
                 profileController pp= s.getLoader().getController() ;
                 pp.setValues( str1);

             }



        } catch (SQLException e) {
            System.out.println("Deja exist");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nom d'utilisateur Deja exist !");
            alert.show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
