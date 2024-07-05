package com.myguilds.controller;

import com.myguilds.config.UserService;
import com.myguilds.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsuarioController {
    @Autowired
    private UserService userService;

    @PostMapping("/registro")
    public Usuario createUser(@RequestBody Usuario user) {
        return userService.saveUser(user);
    }
}
