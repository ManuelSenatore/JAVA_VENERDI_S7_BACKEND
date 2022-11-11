package com.example.java_venerdi_s7.controller;


import com.example.java_venerdi_s7.entities.Misurazione;
import com.example.java_venerdi_s7.entities.Sonda;
import com.example.java_venerdi_s7.services.MisurazioneService;
import com.example.java_venerdi_s7.services.SondaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/misurazioni")
public class MisurazioneController {

    @Autowired
    MisurazioneService ms;

    @Autowired
    SondaService ss;

    @GetMapping("")
    public List<Misurazione> getAllPayments() {
        return ms.getAll();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Misurazione>> getAllPageable( Pageable p) {
        Page<Misurazione> findAll = ms.getAllPaginate(p);

        if (findAll.hasContent()) {
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Misurazione> getById(@PathVariable Long id) throws Exception {

        return new ResponseEntity<>(ms.getById(id), HttpStatus.OK);

    }

    @PostMapping("/new")
    public void create(
            @RequestParam("sonda_id") Long sonda_id,
            @RequestParam("smoke_level") int smoke_level) throws Exception {

        Sonda so = ss.getById(sonda_id);

        if(so != null) {
            Misurazione m = new Misurazione();
            m.setSmokeLevel( smoke_level );
            m.setSonda( so );
            ms.save(m);
        }else {
            throw new Exception("l'utente non ha i permessi per effettuare questa transazione");
        }


    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        try {
            ms.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

