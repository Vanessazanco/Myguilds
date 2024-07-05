package com.myguilds.model;

import lombok.Getter;

@Getter
public enum Cargos {
    LIDER_MARECHAL_GUILD_MASTER("LIDER/MARECHAL/GUILD MASTER"),
    VICE_LIDER_GENERAL("VICE LIDER/GENERAL"),
    MAJOR("MAJOR"),
    CAPITAO("CAPITÃO"),
    MEMBRO("MEMBRO");

    private final String displayName;

    Cargos(String displayName) {
        this.displayName = displayName;
    }

}


