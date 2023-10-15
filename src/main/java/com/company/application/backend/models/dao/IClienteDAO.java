package com.company.application.backend.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.application.backend.models.entity.Cliente;
import com.company.application.backend.models.entity.Region;

public interface IClienteDAO extends JpaRepository<Cliente, Long>{
	@Query("from Region")
	public List<Region> findAllRegiones();
}
