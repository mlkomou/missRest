package com.miss.api.model;

import com.miss.api.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comite")
@Access(AccessType.FIELD)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

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

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;



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
