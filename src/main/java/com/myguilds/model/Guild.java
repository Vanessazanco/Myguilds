package com.myguilds.model;

import com.myguilds.model.DTO.guild.GuildDTO;
import com.myguilds.model.DTO.guild.DadosCadastroGuild;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "guilds")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String jogo;
    private String servidor;

    @Setter
    @Getter
    private LocalDate dataInativacao;

    @OneToMany
    @JoinColumn(name = "guild_id")
    private Set<Membro> membros;

    @OneToMany
    @JoinColumn(name = "guild_id")
    private Set<Cargo> cargos;

    public Guild(DadosCadastroGuild dados) {
        this.jogo = dados.jogo();
        this.nome = dados.nome();
        this.servidor = dados.servidor();

    }

    public void atualizarInformacoes(GuildDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.servidor() != null) {
            this.servidor = dados.servidor();
        }
        if (dados.jogo() != null) {
            this.jogo = dados.jogo();
        }
        this.dataInativacao = dados.dataInativacao();

    }


}
