package com.myguilds.model;

import com.myguilds.model.DTO.membro.MembroDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "membros")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String profissao;
    private String patente;
    private String level;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    public Membro(MembroDTO dados) {
        this.nome = dados.nome();
        this.profissao = dados.profissao();
        this.patente = dados.patente();
        this.level = dados.level();

    }

    public void atualizarInformacoes(MembroDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.profissao() != null) {
            this.profissao = dados.profissao();
        }
        if (dados.patente() != null) {
            this.patente = dados.patente();
        }
        if (dados.patente() != null) {
            this.patente = dados.patente();
        }

    }
}
