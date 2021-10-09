package com.miss.api.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "compteur")
public class Compteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long id;

    @Column(name = "nombre", nullable = false)
    public int nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
