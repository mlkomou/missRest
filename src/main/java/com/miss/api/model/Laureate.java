package com.miss.api.model;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)

public class Laureate extends Generalite {
    @ManyToOne
    @JoinColumn(name="participant", nullable=false)
    private Participante participant;

    public Participante getParticipant() {
        return participant;
    }

    public void setParticipant(Participante participant) {
        this.participant = participant;
    }

    @Override
    public String toString() {
        return "Laureate{" +
                "participant=" + participant +
                ", id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", supprime=" + supprime +
                ", createdBy=" + createdBy +
                '}';
    }
}

