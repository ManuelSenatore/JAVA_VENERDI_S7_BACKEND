package com.example.java_venerdi_s7.repositories;

import com.example.java_venerdi_s7.entities.Misurazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MisurazioneRepository extends JpaRepository<Misurazione, Long> {
}
