package dao.impl;

import dao.GestorDeCuentaDao;
import dao.dto.CategoriasDto;
import dao.dto.GestorDeCuentaDto;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorDeCuentaImpl implements GestorDeCuentaDao {
  private static final String GET_ALL_GESTORCUENTAS = "SELECT * FROM gestordecuenta";
  private static final String GET_GESTORCUENTAS_BY_ID = "SELECT * FROM gestordecuenta WHERE id = ?";
  private final Connection connection;

  public GestorDeCuentaImpl(Connection connection) {
    this.connection = connection;
  }
  @Override
  public List<GestorDeCuentaDto> getAll() throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_GESTORCUENTAS)) {
      ResultSet resultSet = statement.executeQuery();
      List<GestorDeCuentaDto> gestorDeCuentaDtos = new ArrayList<>();
      // Iteramos el ResultSet para agregar el gasto a la lista
      // y mientras agregamos, realizamo el mapeo de cada item
      while (resultSet.next()) {
        gestorDeCuentaDtos.add(mapDtoToGestorDeCuentasDto(resultSet));
      }
      return gestorDeCuentaDtos;
    } catch (SQLException e) {
      throw new DAOException("Error al obtener la lista de Cuentas", e);
    }
  }

  @Override
  public GestorDeCuentaDto getById(int id) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(GET_GESTORCUENTAS_BY_ID)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return mapResultSetGestorDeCuentasDto(resultSet);
      }
      return null;
    } catch (SQLException e) {
      throw new DAOException("Error al obtener el gasto por ID", e);
    }
  }

  private GestorDeCuentaDto mapDtoToGestorDeCuentasDto(ResultSet resultSet) throws SQLException {
    GestorDeCuentaDto gestorDeCuentaDto = new GestorDeCuentaDto();
    gestorDeCuentaDto.setId(resultSet.getInt("id"));
    gestorDeCuentaDto.setTipoDeCuentaId(resultSet.getInt("tipoDeCuentaId"));
    gestorDeCuentaDto.setDescripcion(resultSet.getString("descripcion"));
    gestorDeCuentaDto.setSaldoInicial(resultSet.getDouble("saldoInicial"));
    return gestorDeCuentaDto;
  }
  private GestorDeCuentaDto mapResultSetGestorDeCuentasDto(ResultSet resultSet) throws SQLException {
    GestorDeCuentaDto gestorDeCuentaDto = new GestorDeCuentaDto();
    gestorDeCuentaDto.setId(resultSet.getInt("id"));
    gestorDeCuentaDto.setTipoDeCuentaId(resultSet.getInt("tipoDeCuentaId"));
    gestorDeCuentaDto.setDescripcion(resultSet.getString("descripcion"));
    gestorDeCuentaDto.setSaldoInicial(resultSet.getDouble("saldoInicial"));
    return gestorDeCuentaDto;
  }
}

