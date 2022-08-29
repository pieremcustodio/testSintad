package com.practica.repositories;

import com.practica.models.ERole;
import com.practica.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByName(ERole name);
}
