package com.miss.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "academie")
public class Academie extends Generalite {

    @Column(name = "nom")
    private String nom;

    @Column(name = "contact")
    private String contact;

    @Column(name = "mail")
    private String mail;

    @Column(name = "etat")
    private boolean etat;

    @JsonIgnore
    @OneToMany(mappedBy = "academie")
    private Set<Participante> participante;

    @JsonIgnore
    @OneToMany(mappedBy = "academie")
    private Set<Accompagnatrice> accompagnatrices;

    public Set<Participante> getParticipante() {
        return participante;
    }

    public void setParticipante(Set<Participante> participante) {
        this.participante = participante;
    }

    public Set<Accompagnatrice> getAccompagnatrices() {
        return accompagnatrices;
    }

    public void setAccompagnatrices(Set<Accompagnatrice> accompagnatrices) {
        this.accompagnatrices = accompagnatrices;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Academie{" +
                "nom='" + nom + '\'' +
                ", etat=" + etat +
                ", participante=" + participante +
                ", accompagnatrices=" + accompagnatrices +
                ", id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", supprime=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}
