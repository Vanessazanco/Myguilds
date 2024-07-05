package com.myguilds.repository;

import com.myguilds.model.Cargo;
import com.myguilds.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CargoRepository extends JpaRepository <Cargo,Long>{
}
