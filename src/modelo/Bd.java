package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bd {

  public static Connection getConexion() {

    String claseDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String cadConexion = String.format(
        "jdbc:sqlserver://%s:%s;database=%s;encrypt=false;allowMultiQueries=true",
        "127.0.0.1",
        "14331",
        "Recetas");

    try {

      Class.forName(claseDriver);
      Connection con = DriverManager.getConnection(cadConexion, "sa", "*Cesur?2022./");
      System.out.println("Conexión con la base de datos establecida correctamente");

      return con;

    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

}
