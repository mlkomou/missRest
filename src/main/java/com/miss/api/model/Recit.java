package com.miss.api.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "recit")
public class Recit extends Generalite {

    @Column(name = "titre")
    private String titre;

    @Lob
    @Column(name = "description")
    private String description;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
