package com.example.gestionecolesup;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.xml.stream.Location;
import java.io.IOException;

public class scene {
    //cette class permet le changement des scenes apres chaque action
    public Scene scene;
    public FXMLLoader loader ;
    public Stage stage ;

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void changeScene(ActionEvent event , String Location , String Title, double Width , double Height) throws IOException {
        loader=new FXMLLoader(getClass().getResource(Location));
        Parent root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root,Width, Height);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.setTitle(Title);
        stage.setResizable(false);
        stage.show();
    }}

