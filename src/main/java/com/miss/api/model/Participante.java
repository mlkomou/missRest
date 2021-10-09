package com.miss.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miss.api.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "participante")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Participante extends Generalite {
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "isLaureate")
    private String isLaureate;

    @Column(name = "matricule")
    private String matricule;

    @ManyToOne
    @JoinColumn(name="ecole", nullable=false)
    private Ecole ecole;

    @ManyToOne
    @JoinColumn(name="classe", nullable=false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name="annee", nullable=false)
    private Annee annee;

    @ManyToOne
    @JoinColumn(name="acdemie", nullable=false)
    private Academie academie;

    @Column(name = "dateNaissance")
    private Date dateNaissance;

    @Column(name = "lieuNaissance")
    private String lieuNaissance;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telephone")
    private int telephone;

    @Column(name = "telephoneTuteur")
    private int telephoneTuteur;

    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "participant")
    private Set<Laureate> laureate;


    @ManyToOne
    @JoinColumn(name="suivi8emeId")
    private Suivi8eme suivi8eme;

    @ManyToOne
    @JoinColumn(name="suivi3eme6emeId")
    private Suivi3eme8eme suivi3eme6eme;

    @ManyToOne
    @JoinColumn(name="suivi10eme12emeId")
    private Suivi10eme12eme suivi10eme12eme;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


    @Override
    public String toString() {
        return "Participante{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", ecole=" + ecole +
                ", classe=" + classe +
                ", annee=" + annee +
                ", academie=" + academie +
                ", dateNaissance=" + dateNaissance +
                ", mail='" + mail + '\'' +
                ", telephone=" + telephone +
                ", telephoneTuteur=" + telephoneTuteur +
                ", photo=" + photo +
                ", id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", delete=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}
