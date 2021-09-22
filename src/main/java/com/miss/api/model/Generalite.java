package com.miss.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Access(AccessType.FIELD)
public class Generalite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long id;

    @Column(name = "dateCreation", nullable = false)
    public Date dateCreation;
    @Column(name = "dateModification", nullable = false)
    public Date dateModification;
    @Column(name = "supprime", nullable = false)
    public boolean supprime;
    @Column(name = "createdBy", nullable = false)
    public int createdBy;

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public boolean isDelete() {
        return supprime;
    }

    public void setDelete(boolean supprime) {
        this.supprime = supprime;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Generalite{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", supprime=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}
