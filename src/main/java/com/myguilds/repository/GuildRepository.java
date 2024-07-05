package com.myguilds.repository;

import com.myguilds.model.Guild;
import com.myguilds.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuildRepository extends JpaRepository<Guild,Long> {
    //@Query("SELECT g FROM Guild g WHERE LOWER(g.nome) LIKE LOWER(concat('%', :nome, '%'))")
    //Page<GuildDTO> findByNomeContainingIgnoreCaseAndCargosUsuarioId(@Param("nome") String nome,Long UsuarioId, Pageable pageable);

    @Query(value = "SELECT * FROM guilds g WHERE g.data_inativacao <= CURRENT_DATE - INTERVAL '15 days'",nativeQuery = true)
    List<Guild> findGuildComMaidDe15DiasDeInativacao();

    @Query("SELECT g FROM Guild g JOIN g.cargos c WHERE g.nome LIKE %:nome% AND c.usuario.id = :usuarioId")
    Page<Guild> findByNomeContainingIgnoreCaseAndUsuarioComCargo(@Param("nome") String nome, @Param("usuarioId") Long usuarioId, Pageable pageable);

    @Query("SELECT g FROM Guild g JOIN g.cargos c WHERE c.usuario.id = :usuarioId")
    Page<Guild> findAllByUsuarioComCargo(@Param("usuarioId") Long usuarioId, Pageable pageable);


}
