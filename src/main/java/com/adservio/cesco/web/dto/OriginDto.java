package com.adservio.cesco.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class OriginDto {


    @NotNull
    public String libelle;

    @NotNull
    public Date dateDebut;
}
