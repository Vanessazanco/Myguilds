package com.myguilds.model.DTO.membro;


import com.myguilds.model.Membro;
import jakarta.validation.constraints.NotBlank;


public record MembroDTO(

        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String profissao,
        String patente,
        String level

) {
        public MembroDTO(Membro dados) {
                this(
                        dados.getId(),
                        dados.getNome(),
                        dados.getProfissao(),
                        dados.getPatente(),
                        dados.getLevel()

                );
        }

}