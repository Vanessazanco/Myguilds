package com.myguilds.model.DTO.cargo;

import com.myguilds.model.Cargo;
import com.myguilds.model.Cargos;
import jakarta.validation.constraints.NotBlank;

public record CargoDTO(

        Long id,
        @NotBlank
        String usuarioEmail,
        @NotBlank
        Cargos cargo

) {
   /* public CargoDTO(Cargo cargo) {
        this(
                cargo.getId(),
                cargo.getUsuario(),
                cargo.getCargo()

        );
    }*/
}
