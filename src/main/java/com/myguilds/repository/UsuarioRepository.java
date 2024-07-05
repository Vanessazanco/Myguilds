package com.myguilds.repository;

import com.myguilds.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String username);

    boolean existsByLogin(String username);

    @Query("SELECT u FROM Usuario u WHERE u.login = :usuarioEmail")
    Optional<Usuario> findByEmail(@Param("usuarioEmail") String usuarioEmail);
}
