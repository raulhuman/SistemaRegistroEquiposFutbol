package edu.pe.idat.app.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.app.models.entity.Categoria;

public interface ICategoria extends JpaRepository<Categoria, Integer>{


}
