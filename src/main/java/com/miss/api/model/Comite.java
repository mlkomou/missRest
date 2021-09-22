package com.miss.api.model;

import javax.persistence.*;

@Entity
@Table(name = "comite")
@Access(AccessType.FIELD)
public class Comite extends Generalite {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "structure")
    private String structure;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "photo")
    private String photo;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Comite{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", structure='" + structure + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", photo='" + photo + '\'' +
                ", id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", supprime=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}
