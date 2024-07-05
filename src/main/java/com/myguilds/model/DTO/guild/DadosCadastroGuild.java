package com.myguilds.model.DTO.guild;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

//Campos que chegam da requisição
//Usei Bean Validation para validações
public record DadosCadastroGuild(

        @NotBlank
        String nome,
        String jogo,
        String servidor

) {

}
