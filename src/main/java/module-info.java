module com.example.gestionecolesup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestionecolesup to javafx.fxml;
    exports com.example.gestionecolesup;
    exports  com.example.gestionecolesup.dao;
    opens com.example.gestionecolesup.dao to javafx.fxml;
    exports com.example.gestionecolesup.model;
    opens com.example.gestionecolesup.model to javafx.fxml;

}