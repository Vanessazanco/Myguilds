package com.myguilds.controller;



import com.myguilds.model.DTO.guild.GuildDTO;
import com.myguilds.model.DTO.membro.MembroDTO;
import com.myguilds.model.Guild;
import com.myguilds.model.Membro;
import com.myguilds.repository.MembroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("membros")
public class MembroController {

    @Autowired
    private MembroRepository repository;

    @PutMapping("/{id}")
    public MembroDTO atulizarMembros(@PathVariable Long id,@RequestBody @Valid MembroDTO dados){
        var membros = repository.getReferenceById(dados.id());
        membros.atualizarInformacoes(dados);
        repository.save(membros);
        return new MembroDTO(membros);
    }
    @DeleteMapping("/{id}")
    public void deletarMembros(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }
}
