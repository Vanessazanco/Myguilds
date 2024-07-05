package com.myguilds.config;

import com.myguilds.model.Usuario;
import com.myguilds.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario saveUser(Usuario user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }
}