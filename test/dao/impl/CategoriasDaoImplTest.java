package dao.impl;

import dao.CategoriasDao;
import dao.dto.CategoriasDto;
import entities.Categorias;
import exceptions.DAOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoriasDaoImplTest {
  @Mock
  private Connection mockConnection;
  @Mock
  private PreparedStatement mockPreparedStatement;
  @Mock
  private ResultSet mockResultSet;

  private CategoriasDao categoriasDao;

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.initMocks(this);
    mockConnection.createStatement();
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    categoriasDao = new CategoriasDaoImpl(mockConnection);
  }

  @Test
  void insert_WhenValid() throws SQLException, DAOException {
// GIVEN
    CategoriasDto categoriasDto = new CategoriasDto();
    categoriasDto.setNombre("Ingresos");
    categoriasDto.setTipoOperacionId(1);

    when(mockPreparedStatement.executeUpdate()).thenReturn(1);

    // WHEN
    categoriasDao.insert(categoriasDto);

    // THEN
    verify(mockPreparedStatement).setString(1, categoriasDto.getNombre());
    verify(mockPreparedStatement).executeUpdate();

  }

  @Test
  void getCategoriaByName_WhenValidName() throws SQLException, DAOException {
    // GIVEN
    String name = "Sueldo";
    int operacion = 1;
    int categoriaId = 1;

    when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getInt("id")).thenReturn(categoriaId);
    when(mockResultSet.getString("nombre")).thenReturn(name);

    // WHEN
    Categorias resultCategory = categoriasDao.getByName(name);

    // THEN
    verify(mockPreparedStatement).setString(1, name);
    verify(mockPreparedStatement).executeQuery();
    verify(mockResultSet).next();
    verify(mockResultSet).getInt("id");
    verify(mockResultSet).getString("nombre");

    // Assertions
    assert resultCategory != null;
    assert resultCategory.getId() == categoriaId;
    assert resultCategory.getNombre().equals(name);
  }
}