package com.myguilds.scheduled;

import com.myguilds.model.Guild;
import com.myguilds.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class AgendamentoGuilds {

    @Autowired
    private GuildRepository repository;

    @Scheduled(cron = "0 0 0 * * ?")  // Executa diariamente à meia-noite
    public void deletarGuild(){
        List<Guild> guildParaDeletar = repository.findGuildComMaidDe15DiasDeInativacao();
        repository.deleteAll(guildParaDeletar);
    }
}
