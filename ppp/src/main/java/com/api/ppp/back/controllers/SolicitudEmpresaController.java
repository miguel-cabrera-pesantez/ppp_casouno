package com.api.ppp.back.controllers;

import com.api.ppp.back.models.SolicitudEmpresa;
import com.api.ppp.back.services.SolicitudEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudEmpresa")
public class SolicitudEmpresaController {

    @Autowired
    private SolicitudEmpresaService service;

    // To list all records
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    // To find one record, specifically by a unique identifier (PK or ID)
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarID(@PathVariable("id") Integer id) {
        Optional<SolicitudEmpresa> current = service.findById(id);
        if(current.isPresent()) {
            return ResponseEntity.ok().body(current.get());
        }
        return ResponseEntity.notFound().build();
    }

    // To create a record
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody SolicitudEmpresa entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    // To find one record and update it, specifically by a unique identifier (PK or ID)
    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer id, @RequestBody SolicitudEmpresa entity) {
        Optional<SolicitudEmpresa> optional = service.findById(id);
        if(optional.isPresent()) {
            SolicitudEmpresa current = optional.get();
            current.setConvenio(entity.getConvenio());
            current.setNumPracticantes(entity.getNumPracticantes());
            current.setNumHoras(entity.getNumHoras());
            current.setFechaInicioTen(entity.getFechaInicioTen());
            current.setFechaMaxTen(entity.getFechaMaxTen());
            current.setEstado(entity.getEstado());
            current.setUrl(entity.getUrl());
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
    @PostMapping("/guardarpdf")
    public ResponseEntity<String> guardarDocumento(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id")Integer id) {
        try {
            Optional<SolicitudEmpresa> optional = service.findById(id);
            if (optional.isPresent()) {
                SolicitudEmpresa current = optional.get();
                current.setUrl(archivo.getBytes());
                service.save(current);
                return ResponseEntity.ok("El documento se ha guardado correctamente. "+current.getUrl().length);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el documento.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el documento.");
    }

    @GetMapping("/mostrarpdf/{id}")
    public ResponseEntity<byte[]> obtenerDocumento(@PathVariable("id") Integer id) {
        Optional<SolicitudEmpresa> optional = service.findById(id);
        if (optional.isPresent()) {
            SolicitudEmpresa current = optional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename(current.getId()+".pdf").build());
            return new ResponseEntity<>(current.getUrl(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // To find by ESTADO 1 ENVIADA
    @GetMapping("/estadoEnviado")
    public ResponseEntity<?> listarPorEstadoEnviado() {
        return ResponseEntity.ok().body(service.listarPorEstadoEnviado());
    }

    // To find by ESTADO 2 ACEPTADA
    @GetMapping("/estadoAceptado")
    public ResponseEntity<?> listarPorEstadoAceptado() {
        return ResponseEntity.ok().body(service.listarPorEstadoAceptadoo());
    }

}
