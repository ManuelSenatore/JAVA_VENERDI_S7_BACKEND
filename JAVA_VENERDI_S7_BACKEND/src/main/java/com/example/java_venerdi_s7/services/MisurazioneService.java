package com.example.java_venerdi_s7.services;

import com.example.java_venerdi_s7.entities.Misurazione;
import com.example.java_venerdi_s7.entities.Sonda;
import com.example.java_venerdi_s7.repositories.MisurazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MisurazioneService {

    @Autowired
    MisurazioneRepository mr;

    public void save( Misurazione u) {
        mr.save(u);
    }

    public List<Misurazione> getAll() {
        return mr.findAll();
    }

    public Misurazione getById(Long id) throws Exception {
        Optional<Misurazione> misurazione = mr.findById(id);
        if ( misurazione.isEmpty() )
            throw new Exception("User not available");
        return misurazione.get();
    }

    public void delete(Long id) throws Exception {
        Optional<Misurazione> u = mr.findById(id);
        if (u.isPresent()) {
            mr.delete(u.get());
        } else {
            throw new Exception("Misurazione non trovato");
        }
    }

    public void update(Misurazione u) {
        mr.save(u);
    }

    public Page<Misurazione> getAllPaginate(Pageable p) {
        return mr.findAll(p);
    }
}
