package com.example.gestionecolesup.dao;

import javafx.collections.ObservableList;
import com.example.gestionecolesup.dao.*;
import com.example.gestionecolesup.model.*;

public interface Congedao {
    public  void saveconge(Conge c);
    public ObservableList<Conge> ShowListConge();
}
