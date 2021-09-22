package com.miss.api.model;

import java.util.Date;

public class ParticipanteSimple {
    public Long academieId;
    public Long ecoleid;
    public Long classeId;
    public Long anneeId;
    public String prenom;
    public String nom;
    public Date dateNaissance;
    public String lieuNaissance;
    public String mail;
    public int telephone;
    public int telephoneTuteur;


    public Long getAcademieId() {
        return academieId;
    }

    public void setAcademieId(Long academieId) {
        this.academieId = academieId;
    }

    public Long getEcoleid() {
        return ecoleid;
    }

    public void setEcoleid(Long ecoleid) {
        this.ecoleid = ecoleid;
    }

    public Long getClasseId() {
        return classeId;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public Long getAnneeId() {
        return anneeId;
    }

    public void setAnneeId(Long anneeId) {
        this.anneeId = anneeId;
    }

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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getTelephoneTuteur() {
        return telephoneTuteur;
    }

    public void setTelephoneTuteur(int telephoneTuteur) {
        this.telephoneTuteur = telephoneTuteur;
    }
}
