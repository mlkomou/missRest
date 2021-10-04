package com.miss.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Access(AccessType.FIELD)
@Table(name = "suivi3eme6eme")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suivi3eme8eme extends Generalite {
    @Column(name = "date")
    private String date;

    @Column(name = "observationEncadreur")
    private String observationEncadreur;

    @Column(name = "observationParent")
    private String observationParent;

    @Column(name = "calculTrimestre1")
    private Long calculTrimestre1;

    @Column(name = "calculTrimestre2")
    private Long calculTrimestre2;

    @Column(name = "calculTrimestre3")
    private Long calculTrimestre3;

    @Column(name = "lectureEcritureTrimestre1")
    private Long lectureEcritureTrimestre1;

    @Column(name = "lectureEcritureTrimestre2")
    private Long lectureEcritureTrimestre2;

    @Column(name = "lectureEcritureTrimestre3")
    private Long lectureEcritureTrimestre3;

    @Column(name = "sciencesObsTrimestre1")
    private Long sciencesObsTrimestre1;

    @Column(name = "sciencesObsTrimestre2")
    private Long sciencesObsTrimestre2;

    @Column(name = "sciencesObsTrimestre3")
    private Long sciencesObsTrimestre3;
}
