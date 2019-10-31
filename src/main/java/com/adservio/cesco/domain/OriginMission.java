package com.adservio.cesco.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * OriginMission POJO
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OriginMission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    public String libelle;

    @Column(name = "date_debut")
    @NotNull
    public Date dateDebut;

    @Column(name = "date_fin")
    public Date dateFin;

    @Column(name = "is_active")
    public boolean isActive;

}
