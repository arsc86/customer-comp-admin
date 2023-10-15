package com.company.application.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.application.backend.models.entity.Factura;

public interface IFacturaDAO extends CrudRepository<Factura, Long>{

}
