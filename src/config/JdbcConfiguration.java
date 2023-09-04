package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfiguration {
  private static final String DB_DRIVER = "org.h2.Driver";
  //private static final String DB_CONNECTION = "jdbc:h2:~/gastos";
  private static final String DB_CONNECTION = "jdbc:h2:~/gastos;INIT=RUNSCRIPT FROM 'classpath:create_db.sql'";
  private static final String DB_USER = "sa";
  private static final String DB_PASSWORD = "";

  //Método estático para establecer la conección
  public static Connection getDBConnection(){
    Connection connection = null;
    try {
      Class.forName(DB_DRIVER);
      connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    } catch (ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }
    return  connection;
  }

}

