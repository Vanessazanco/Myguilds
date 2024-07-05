package com.myguilds.model.DTO.guild;

import com.myguilds.model.Guild;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GuildDTO(

        Long id,
        String nome,
        String jogo,
        String servidor,
        LocalDate dataInativacao
) {
    public GuildDTO(Guild dados) {
        this(
                dados.getId(),
                dados.getNome(),
                dados.getJogo(),
                dados.getServidor(),
                dados.getDataInativacao()
        );
    }

}
