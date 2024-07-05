package com.myguilds.controller;

import com.myguilds.model.*;
import com.myguilds.model.DTO.cargo.CargoDTO;
import com.myguilds.model.DTO.guild.GuildDTO;
import com.myguilds.model.DTO.guild.*;
import com.myguilds.model.DTO.membro.MembroDTO;
import com.myguilds.repository.GuildRepository;
import com.myguilds.repository.MembroRepository;
import com.myguilds.service.GuildService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("guilds")
public class GuildController {

    @Autowired
    private MembroRepository repositoryMembros;
    @Autowired
    private GuildService guildService;
    @Autowired
    private GuildRepository repository;

    @PostMapping
    public void cadastrarGuild(@RequestBody @Valid DadosCadastroGuild dados) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Guild guild = new Guild(dados);
        guildService.cadastrarGuild(guild, usuario);
    }

    @PostMapping("/{id}/cadastrarCargo")
    public ResponseEntity<String> cadastrarCargo(@PathVariable Long id, @RequestBody CargoDTO cargo) {
        guildService.cadastrarCargo(id, cargo.usuarioEmail(), cargo.cargo());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

   /* @PostMapping("/{id}/cadastrarCargo")
    public void cadastrarCargo(@PathVariable Long id, @RequestBody CargoDTO cargo) {
        guildService.cadastrarCargo(id,cargo.usuarioEmail(),cargo.cargo());
    }*/

    @GetMapping
    public Page<GuildDTO> listarGuild(@RequestParam(required = false) String nome, @PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (nome != null && !nome.isEmpty()) {
            return repository.findByNomeContainingIgnoreCaseAndUsuarioComCargo(nome, usuario.getId(), paginacao).map(GuildDTO::new);
        } else {
            return repository.findAllByUsuarioComCargo(usuario.getId(), paginacao)
                    .map(GuildDTO::new);
        }
    }

    @GetMapping("/{id}")
    public GuildDTO listarPorId(@PathVariable Long id) {
        var guild = repository.getReferenceById(id);
        return new GuildDTO(guild);
    }

    @DeleteMapping("/{id}")
    public void deletarGuild(@PathVariable Long id) {
        Optional<Guild> guildOpt = repository.findById(id);
        if (guildOpt.isPresent() && guildOpt.get().getDataInativacao() == null) {
            Guild guild = guildOpt.get();
            guild.setDataInativacao(LocalDate.now());
            repository.save(guild);
        }
    }

    @PutMapping("/{id}")
    public GuildDTO atualizarGuild(@PathVariable Long id, @RequestBody @Valid GuildDTO dados) {
        var guilds = repository.getReferenceById(id);
        guilds.atualizarInformacoes(dados);
        repository.save(guilds);
        return new GuildDTO(guilds);
    }

    @PutMapping("{id}/reativar")
    public GuildDTO reativar(@PathVariable Long id) {
        var guilds = repository.getReferenceById(id);
        guilds.setDataInativacao(null);
        repository.save(guilds);
        return new GuildDTO(guilds);

    }

    /* Membros */

    @PostMapping("/{id}/membros")
    public void cadastrarMembros(@PathVariable Long id, @RequestBody @Valid MembroDTO dados) {
        Membro membro = new Membro(dados);
        membro.setGuild(new Guild());
        membro.getGuild().setId(id);
        repositoryMembros.save(membro);
    }

    @GetMapping("/{id}/membros")
    public Page<MembroDTO> listarMembros(@PathVariable Long id, @RequestParam(required = false) String nome, @PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        if (nome != null && !nome.isEmpty()) {
            return repositoryMembros.findByGuildIdAndNomeContainingIgnoreCase(id, nome, paginacao);
        } else {
            return repositoryMembros.findByGuildId(id, paginacao);
        }
    }
}
