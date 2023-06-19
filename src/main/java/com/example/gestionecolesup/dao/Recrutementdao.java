package com.example.gestionecolesup.dao;
import javafx.collections.ObservableList;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;

public interface Recrutementdao {
    public void save(Recrutement r);

    public ObservableList<Recrutement> ShowListDemande();


}
