package com.api.ppp.back.services;

import com.api.ppp.back.models.*;

import java.util.List;

import java.util.Optional;

public interface PracticaService extends BaseService<Practica, Integer> {

    List<Practica> findByTutorInstitutoUsuarioId(Integer id);

    List<Practica> findByTutorEmpresarialUsuarioId(Integer id);

    public List<Practica> practicaxDocente(TutorInstituto tutorInstituto);

    public Practica practicaxEstudiante(Estudiante estudiante);

    List<Practica> practicaxEmpresa(TutorEmpresarial tutorInstituto);

    List<Practica> practicaxEstudianteUsuario(Integer id);

    List<Practica> findByConvocatoriaId(Integer id);

    List<Practica> findByConvocatoriaSolicitudEmpresaConvenioEmpresa(Empresa empresa);

}
