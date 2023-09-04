package dao;

import dao.dto.CategoriasDto;
import entities.Categorias;
import exceptions.DAOException;

import java.util.List;

public interface CategoriasDao {
  CategoriasDto getById(int id) throws DAOException;
  Categorias getByName(String name) throws DAOException;
  List<CategoriasDto> getAll() throws DAOException;
  void insert(CategoriasDto categoriasDto) throws DAOException;
  void update(CategoriasDto categoriasDto) throws DAOException;
  void delete(int id) throws DAOException;
}
