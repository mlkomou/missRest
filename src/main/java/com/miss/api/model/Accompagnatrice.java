package com.miss.api.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "accompagnatrice")
public class Accompagnatrice extends Generalite {
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @ManyToOne
    @JoinColumn(name="academie", nullable=false)
    private Academie academie;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Academie getAcademie() {
        return academie;
    }

    public void setAcademie(Academie academie) {
        this.academie = academie;
    }
}
