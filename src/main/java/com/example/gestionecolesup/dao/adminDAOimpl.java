package com.example.gestionecolesup.dao;


 import com.example.gestionecolesup.DashBoradController;
 import com.example.gestionecolesup.model.Admin;
 import com.example.gestionecolesup.scene;
 import javafx.event.ActionEvent;
 import javafx.scene.control.Alert;

 import java.io.IOException;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
import java.sql.Statement;
// L'ajout d admin: lance des  tests: si le nom d utilisteur deja utilise , si un numero de telephone deja utilise , si un numero de telephone est invalide cad n'exist pas dans la table admin_reset
public class adminDAOimpl implements AdminDAO {
        @Override

        public void addAdmin(ActionEvent event, Admin a)
        {
            daoFactory conDB = new daoFactory();
            Statement stmt;
       PreparedStatement stm;
            PreparedStatement stmt1=null;
            ResultSet Res = null;
            try
            {
  stm =conDB.getCon().prepareStatement("SELECT is_phone_number_used  FROM admin_reset  WHERE tel=? ");
                stm.setString(1,a.getTel());
                Res=stm.executeQuery();
                boolean is_phone_already_set=false;
                while(Res.next()){
                Boolean r= Res.getBoolean("is_phone_number_used");
                if(r){
                    System.out.println("Erreur  :Numero de telephone est deja utilise");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Numero de telephone est deja utilise  !");
                    alert.show();
                    System.out.println(r);
                    is_phone_already_set=true;
                    break;
               }}
if( !is_phone_already_set){
                    stmt = conDB.getCon().createStatement();
                    String req="INSERT INTO admins VALUES('"+a.getNom()+"','"+a.getPrenom()+"','"+a.getNomUtilisateur()+"','"+a.getTel()+"','"+a.getMotDePass()+"')";
                    stmt.executeUpdate(req);


    System.out.println("success!");
    Alert alert=new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Vous avez cree votre compte avec success !");
    alert.show();
    scene s = new scene();
    s.changeScene( event,"Login.fxml" ,  "Se Connecter", 960 , 640  );
}
                String req1="UPDATE admin_reset "
                        + "SET is_phone_number_used =? "
                        + "WHERE tel =?";
                stmt1 =conDB.getCon().prepareStatement(req1);
                stmt1.setBoolean(1,true);
                stmt1.setString(2,a.getTel());
                int res = stmt1.executeUpdate();
                if (res > 0){
                    System.out.println(
                            "table successfully updated.");}
                else{
                    System.out.println(
                            "table successfully updated.");}
                conDB.getCon().close();
            } catch (SQLException e) {

                System.out.println("duplicate!");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nom d'utilisateur est  deja exist !");
                alert.show();

                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Admin  has been added successfully !");


        }
    public  void Seconnecter(ActionEvent event , String username , String password){

            daoFactory conDB = new daoFactory();
           PreparedStatement stmt=null;
            ResultSet resultSet=null;
String nc=null;
String tl=null;
String nu = null;
        PreparedStatement _st=null;
        ResultSet _res =null;
            try {
                stmt =conDB.getCon().prepareStatement("SELECT motDePass  FROM admins  WHERE nomUtilisateur=? ");
                stmt.setString(1,username);
                resultSet=stmt.executeQuery();
                if(!resultSet.isBeforeFirst()){
                    System.out.println("User not found in the db");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Les données insérées sont incorrectes !");
                    alert.show();
                }else{
                    while(resultSet.next()){
                        String retrievedPassword = resultSet.getString("motdePass");

                        if(retrievedPassword.equals(password)){
                            _st=conDB.getCon().prepareStatement("select * from admins where nomUtilisateur =?");
                            _st.setString(1,username);
                            _res=_st.executeQuery();
                            while(_res.next()){
                                 nc= _res.getString("nom")+"  "+ _res.getString("prenom");
                                 tl = _res.getString("tel");
                                 nu=_res.getString("nomUtilisateur");
                            }

                            scene s = new scene();

                            s.changeScene(event,"Dashboard.fxml" ,  "\n" +
                                    "Gestion d'école", 960 , 640 );
                            DashBoradController d= s.getLoader().getController() ;
                              d.RetriveValues( nc ,  tl,nu);


                            break;
                        }else{
                            System.out.println("Mot de passe est incorrect");
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Mot de passe est incorrect ");
                            alert.show();
                            break;
                        }
                    }
                }


            }catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



    }
    //ici envoyer permet l'admin a changer sans mot de passe si le code confidentiel est valide
public void Envoyer(ActionEvent event , String tel , String codeConfidentiel  ){
    daoFactory conDB = new daoFactory();

    PreparedStatement stmt=null;
    ResultSet resultSet=null;

    try {
        stmt =conDB.getCon().prepareStatement("SELECT codeConfidentiel  FROM admin_reset  WHERE tel=? ");
        stmt.setString(1,tel);
        resultSet=stmt.executeQuery();
        if(!resultSet.isBeforeFirst()){
            System.out.println("phone number is not exist in the database ");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le Numero de telephone que vous avez inserver n'exist pas dans la base de données !");
            alert.show();
        }else{
            while(resultSet.next()){
                String retrievedPassword = resultSet.getString("codeConfidentiel");

                if(retrievedPassword.equals(codeConfidentiel)){
                    scene s = new scene();
                    s.changeScene(event,"ResetPwd.fxml" ,  "\n" +
                            "Definir un neveau mot de pass ", 960 , 640 );
                }else{
                    System.out.println("confidential code erro  ");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Le code confidentiel que vous avez inserez est incorrect  ");
                    alert.show();
                    break;
                }
            }
        }


    }catch (SQLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    System.out.println("success  !");

}
//la changement de mot de pass
        public void UpdatePwd(ActionEvent event , String mdp1, String tel  ){
            daoFactory conDB = new daoFactory();

            PreparedStatement stmt=null;

            try {
                String req="UPDATE admins "
                        + "SET motDePass =? "
                        + "WHERE tel =?";
                stmt =conDB.getCon().prepareStatement(req);
                stmt.setString(1,mdp1);
                stmt.setString(2,tel);
                int res = stmt.executeUpdate();
                if (res > 0){
                    System.out.println(
                            "\n" +
                                    "la table a été mise à jour avec succès.");
                System.out.println("success  ");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Success  ");
                alert.show();
                    scene s = new scene();
                    s.changeScene(event,"Login.fxml" ,  "\n" +
                            "Se connecter ", 960 , 640 );}
                else{
                    System.out.println("\n" +
                            "Impossible de mettre à jour le mot de passe");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("\n" +
                            "Impossible de mettre à jour le mot de passe ");
                    alert.show();}
                conDB.getCon().close();



    }catch (SQLException e) {
                System.out.println("\n" +
                        "Nom d' utilisateur n'exist pas ");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("\n" +
                        "Nom d' utilisateur n'exist pas");
                alert.show();
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        }


