package com.myguilds.service;

import com.myguilds.excepition.GenectNotFoundException;
import com.myguilds.model.Cargo;
import com.myguilds.model.Cargos;
import com.myguilds.model.Guild;
import com.myguilds.model.Usuario;
import com.myguilds.repository.CargoRepository;
import com.myguilds.repository.GuildRepository;
import com.myguilds.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuildService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public void cadastrarGuild(Guild guild, Usuario usuario) {
        guild = guildRepository.save(guild);

        Cargo cargo = new Cargo();
        cargo.setUsuario(usuario);
        cargo.setCargo(Cargos.LIDER_MARECHAL_GUILD_MASTER);
        cargo.setGuild(guild);
        cargoRepository.save(cargo);
    }

    public void cadastrarCargo(Long guildId, String usuarioEmail, Cargos cargos) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuarioEmail);
        Optional<Guild> optionalGuild = guildRepository.findById(guildId);

        if (!optionalUsuario.isPresent()) {
            throw new GenectNotFoundException("Usuário com EMAIL " + usuarioEmail + " não encontrado");
        }

        if (!optionalGuild.isPresent()) {
            throw new GenectNotFoundException("Guild com ID " + guildId + " não encontrada");
        }

        Usuario usuario = optionalUsuario.get();
        Guild guild = optionalGuild.get();

        Cargo cargo = new Cargo();
        cargo.setUsuario(usuario);
        cargo.setGuild(guild);
        cargo.setCargo(cargos);
        cargoRepository.save(cargo);
    }
}


