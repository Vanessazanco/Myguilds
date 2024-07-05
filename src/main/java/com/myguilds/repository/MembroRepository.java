package com.myguilds.repository;

import com.myguilds.model.DTO.membro.MembroDTO;
import com.myguilds.model.Membro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembroRepository extends JpaRepository<Membro,Long>{

    Page<MembroDTO> findByGuildIdAndNomeContainingIgnoreCase(Long guildId,String nome, Pageable paginacao);
    Page<MembroDTO> findByGuildId(Long guildId, Pageable paginacao);
}
