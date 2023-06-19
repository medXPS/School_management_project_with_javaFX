package com.example.gestionecolesup;
import com.example.gestionecolesup.dao.daoFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
        public class HelloApplication extends Application {
            @Override
            public void start(Stage stage) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 960, 640);
                stage.setTitle("Se connecter ");
                stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("icon.png")));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
        }
    public static void main(String[] args) {
        daoFactory c =  new daoFactory();
        launch(args);
    }
}