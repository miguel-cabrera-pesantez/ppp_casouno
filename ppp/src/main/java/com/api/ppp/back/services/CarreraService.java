package com.api.ppp.back.services;

import com.api.ppp.back.models.Carrera;

public interface CarreraService extends BaseService<Carrera, Integer> {

    public boolean findByIdCarrera(Integer idCarrera);

    Carrera buscarPorIdCarrera(Integer idCarrera);

}
