package com.miss.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Access(AccessType.FIELD)
@Table(name = "suivi8eme")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suivi8eme extends Generalite{
    @Column(name = "date")
    private String date;

    @Column(name = "observationEncadreur")
    private String observationEncadreur;

    @Column(name = "observationParent")
    private String observationParent;




    @Column(name = "mathTrimestre1")
    private Long mathTrimestre1;

    @Column(name = "mathTrimestre2")
    private Long mathTrimestre2;

    @Column(name = "mathTrimestre3")
    private Long mathTrimestre3;

    @Column(name = "phisiqueChimieTrimestre1")
    private Long phisiqueChimieTrimestre1;

    @Column(name = "phisiqueChimieTrimestre2")
    private Long phisiqueChimieTrimestre2;

    @Column(name = "phisiqueChimieTrimestre3")
    private Long phisiqueChimieTrimestre3;

    @Column(name = "biologieTrimestre1")
    private Long biologieTrimestre1;

    @Column(name = "biologieTrimestre2")
    private Long biologieTrimestre2;

    @Column(name = "biologieTrimestre3")
    private Long biologieTrimestre3;

    @Column(name = "moyenneTrimestre1")
    private Long moyenneTrimestre1;

    @Column(name = "moyenneTrimestre2")
    private Long moyenneTrimestre2;

    @Column(name = "moyenneTrimestre3")
    private Long moyenneTrimestre3;
}
