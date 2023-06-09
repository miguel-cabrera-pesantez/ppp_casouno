package com.api.ppp.back.services;

import com.api.ppp.back.daos.BaseRepository;
import com.api.ppp.back.daos.PracticaRepository;
import com.api.ppp.back.exception.ResourceNotFoundException;
import com.api.ppp.back.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticaServiceImpl extends BaseServiceImpl<Practica, Integer> implements PracticaService {

    @Autowired
    private PracticaRepository repository;

    public PracticaServiceImpl(BaseRepository<Practica, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Practica> findByTutorInstitutoUsuarioId(Integer id) {
        List<Practica> practicas = repository.findByTutorInstitutoUsuarioId(id);
        if (practicas.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros relacionados en Practicas con TISTA_ID: " + id);
        }
        return practicas;
    }

    @Override
    public List<Practica> findByTutorEmpresarialUsuarioId(Integer id) {
        List<Practica> practicas = repository.findByTutorEmpresarialUsuarioId(id);
        if (practicas.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros relacionados en Practicas con TEMP_ID: " + id);
        }
        return practicas;
    }

    @Override
    public List<Practica> findByConvocatoriaId(Integer id) {
        List<Practica> practicas = repository.findByConvocatoriaId(id);
        if (practicas.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros relacionados en Practicas con Convocatoria ID: " + id);
        }
        return practicas;
    }

    @Override
    public List<Practica> findByConvocatoriaSolicitudEmpresaConvenioEmpresa(Empresa empresa) {
        List<Practica> practicas = repository.findByConvocatoria_SolicitudEmpresa_Convenio_Empresa(empresa);
        if (practicas.isEmpty()) {
            throw new ResourceNotFoundException("Sin registros relacionados con Practocas y Empresa_ID: " + empresa);
        }
        return practicas;
    }

    @Override
    public List<Practica> practicaxDocente(TutorInstituto tutorInstituto) {
        return repository.findByTutorInstituto(tutorInstituto).orElse(null);
    }

    @Override
    public Practica practicaxEstudiante(Estudiante estudiante) {
        return repository.findByEstudiante(estudiante).orElse(null);
    }

    @Override
    public List<Practica> practicaxEmpresa(TutorEmpresarial tutorInstituto) {
        return repository.findByTutorEmpresarial(tutorInstituto).orElse(null);
    }

    @Override
    public List<Practica> practicaxEstudianteUsuario(Integer id) {
        return repository.findByEstudianteUsuarioId(id);
    }

}
