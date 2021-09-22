package com.miss.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "classe")
public class Classe extends Generalite {
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private boolean etat;

    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    private Set<Participante> participante;



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

    public void setParticipante(Set<Participante> participante) {
        this.participante = participante;
    }

    @Override
    public String toString() {
        return "Classe{" +
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
