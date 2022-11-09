package edu.migsw.marca.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.migsw.marca.entities.MarcaEntity;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {

    @Query("SELECT m from MarcaEntity m WHERE m.rut = :rut")
    ArrayList<MarcaEntity> findByRut(@Param("rut") String rut);

    @Query("SELECT m from MarcaEntity m WHERE m.id = :id")
    Optional<MarcaEntity> findById(@Param("id") Long id);
}