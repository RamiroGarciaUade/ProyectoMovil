package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import java.util.List;

public interface IService<T, R> {
    List<T> list();

    T save(R item);

    T find(Long id);

    void delete(Long id);

    T update(Long id, R i);

}