package com.miss.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "annee")
public class Annee extends Generalite {
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private boolean etat;

    @JsonIgnore
    @OneToMany(mappedBy = "annee")
    private Set<Participante> participante;

    public Set<Participante> getParticipante() {
        return participante;
    }

    public void setParticipante(Set<Participante> participante) {
        this.participante = participante;
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

    @Override
    public String toString() {
        return "Annee{" +
                "etat=" + etat +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", delete=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}
