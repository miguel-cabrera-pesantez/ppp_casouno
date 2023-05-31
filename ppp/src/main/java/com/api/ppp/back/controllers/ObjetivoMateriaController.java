package com.api.ppp.back.controllers;

import com.api.ppp.back.models.Accion;
import com.api.ppp.back.models.Materia;
import com.api.ppp.back.models.ObjetivoMateria;
import com.api.ppp.back.services.MateriaService;
import com.api.ppp.back.services.ObjetivoMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/objetivoMateria")
@RestController
public class ObjetivoMateriaController {

    @Autowired
    private ObjetivoMateriaService service;
    @Autowired
    private MateriaService serviceMateria;

    // To list all records
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    // To find one record, specifically by a unique identifier (PK or ID)
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarID(@PathVariable("id") Integer id) {
        Optional<ObjetivoMateria> current = service.findById(id);
        if(current.isPresent()) {
            return ResponseEntity.ok().body(current.get());
        }
        return ResponseEntity.notFound().build();
    }

    // To create a record
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody ObjetivoMateria entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    // To find one record and update it, specifically by a unique identifier (PK or ID)
    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer id, @RequestBody ObjetivoMateria entity) {
        Optional<ObjetivoMateria> optional = service.findById(id);
        if(optional.isPresent()) {
            ObjetivoMateria current = optional.get();
            current.setDescripcion(entity.getDescripcion());
            current.setMateria(entity.getMateria());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(current));
        }
        return ResponseEntity.notFound().build();
    }

    // To find one record and delete it, specifically by a unique identifier (PK or ID)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarID(@PathVariable("id") Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarxmateria/{id}")
    public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
        Materia materia=serviceMateria.findById(id).orElse(null);
        return ResponseEntity.ok().body(service.objetivoxMateria(materia));
    }

}