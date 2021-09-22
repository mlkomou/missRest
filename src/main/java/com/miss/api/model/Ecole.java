package com.miss.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "ecole")
public class Ecole extends Generalite {
    @Column(name = "nom")
    private String nom;

    @Column(name = "tel")
    private String tel;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "etat")
    private boolean etat;

    @JsonIgnore
    @OneToMany(mappedBy = "ecole")
    private Set<Participante> participante;

    @ManyToOne()
    @JoinColumn(name = "academie")
    Academie academie;


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

    public Set<Participante> getParticipante() {
        return participante;
    }

    public Academie getAcademie() {
        return academie;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setAcademie(Academie academie) {
        this.academie = academie;
    }

    public void setParticipante(Set<Participante> participante) {
        this.participante = participante;
    }

    @Override
    public String toString() {
        return "Ecole{" +
                "nom='" + nom + '\'' +
                ", etat=" + etat +
                ", participante=" + participante +
                ", id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", delete=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}
