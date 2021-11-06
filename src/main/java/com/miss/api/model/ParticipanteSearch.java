package com.miss.api.model;

public class ParticipanteSearch {
    public String prenom;
    public String nom;
    public String academieId;
    public String classeId;
    public String anneeId;

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

    public String getAcademieId() {
        return academieId;
    }

    public void setAcademieId(String academieId) {
        this.academieId = academieId;
    }

    public String getClasseId() {
        return classeId;
    }

    public void setClasseId(String classeId) {
        this.classeId = classeId;
    }

    public String getAnneeId() {
        return anneeId;
    }

    public void setAnneeId(String anneeId) {
        this.anneeId = anneeId;
    }
}
