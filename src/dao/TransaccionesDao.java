package dao;

import dao.dto.TransaccionesDto;
import exceptions.DAOException;

import java.util.List;

public interface TransaccionesDao {
  TransaccionesDto getById(int id) throws DAOException;
  List<TransaccionesDto> getAll() throws DAOException;
  void insert(TransaccionesDto transaccionesDto) throws DAOException;
  void update(TransaccionesDto transaccionesDto) throws DAOException;
  void delete(int id) throws DAOException;
}
