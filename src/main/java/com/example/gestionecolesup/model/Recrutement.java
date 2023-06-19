package com.example.gestionecolesup.model;

import javafx.scene.control.Button;

public class Recrutement {
    private int id;
    private String name;
    private String cin;
    private String email;
    private String phone;
    private String gender;
    private String apply_for;
    private Button button;


    public Recrutement(String name, String cin, String email, String phone, String gender, String apply_for) {

        this.name = name;
        this.cin = cin;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.apply_for = apply_for;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getApply_for() {
        return apply_for;
    }

    public void setApply_for(String apply_for) {
        this.apply_for = apply_for;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Recrutement{" +
                "name='" + name + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", apply_for='" + apply_for + '\'' +
                '}';
    }
}
