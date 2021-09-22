package com.miss.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = "participante")
public class Participante extends Generalite {
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

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

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Academie getAcademie() {
        return academie;
    }

    public void setAcademie(Academie academie) {
        this.academie = academie;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    public String getPhoto() {
        return photo;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Set<Laureate> getLaureate() {
        return laureate;
    }

    public void setLaureate(Set<Laureate> laureate) {
        this.laureate = laureate;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


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
