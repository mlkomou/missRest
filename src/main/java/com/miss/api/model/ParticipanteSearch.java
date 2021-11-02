package com.miss.api.model;

public class ParticipanteSearch {
    public String prenom;
    public String nom;
    public Long academieId;
    public Long classeId;
    public Long anneeId;

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

    public Long getAcademieId() {
        return academieId;
    }

    public void setAcademieId(Long academieId) {
        this.academieId = academieId;
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
}
