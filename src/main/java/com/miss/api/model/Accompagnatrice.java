package com.miss.api.model;

import com.miss.api.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "accompagnatrice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Accompagnatrice extends Generalite {
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @ManyToOne
    @JoinColumn(name="academie", nullable=false)
    private Academie academie;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


}
