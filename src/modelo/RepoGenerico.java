package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.Reflexion;

public class RepoGenerico implements AutoCloseable {

	private Connection conexion;

	public RepoGenerico() {
		conexion = Bd.getConexion();

	}

	public RepoGenerico(Connection conexion) {
		this.conexion = conexion;

	}

	public Connection getConexion() {
		return conexion;
	}

	public int[] insert(String query, Object[] valoresParam) throws SQLException {

		int[] resultados = new int[2];

		PreparedStatement prepStmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		for (int i = 0; i < valoresParam.length; i++) {
				prepStmt.setObject(i + 1, valoresParam[i]);
		}

		resultados[0] = prepStmt.executeUpdate();

		try (ResultSet rs = prepStmt.getGeneratedKeys()) {
			if (rs.next()) {
				resultados[1] = rs.getInt(1);
			}
		}

		return resultados;
	}

	public int[] batchInsert(String query, List<Object[]> datos) throws SQLException {

		PreparedStatement prepStmt = conexion.prepareStatement(query);

		for (Object[] fila : datos) {

			for (int i = 0; i < fila.length; i++) {
				prepStmt.setObject(i + 1, fila[i]);
			}
			prepStmt.addBatch();
		}

		return prepStmt.executeBatch();
	}

	public int execute(String query, Object[] valoresParam) throws SQLException {

		PreparedStatement prepStmt = conexion.prepareStatement(query);

		for (int i = 0; i < valoresParam.length; i++) {
			prepStmt.setObject(i + 1, valoresParam[i]);
		}

		return prepStmt.executeUpdate();
	}

	public <T> boolean getPor(String query, Object[] valoresParam, T objeto) throws SQLException {

		PreparedStatement prepStmt = conexion.prepareStatement(query);

		if (valoresParam != null) {
			for (int i = 0; i < valoresParam.length; i++) {
				prepStmt.setObject(i + 1, valoresParam[i]);
			}
		}

		ResultSet rs = prepStmt.executeQuery();
		ResultSetMetaData rsmd = prepStmt.getMetaData();

		String nombreColumna;

		if (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				nombreColumna = rsmd.getColumnLabel(i);
				Map<String, Class<?>> map = getMapeoTipos();
				Object valor = rs.getObject(nombreColumna, map.get(rsmd.getColumnTypeName(i)));
        
				Reflexion.setValorMetodo(objeto, nombreColumna, valor);
			}
			return true;
		}

		return false;
	}

	public <T> List<T> getPor(String query, Object[] valoresParam, Class<T> clazz) throws SQLException {

		PreparedStatement prepStmt = conexion.prepareStatement(query);

		if (valoresParam != null) {
			for (int i = 0; i < valoresParam.length; i++) {
				prepStmt.setObject(i + 1, valoresParam[i]);
			}
		}

		ResultSet rs = prepStmt.executeQuery();
		ResultSetMetaData rsmd = prepStmt.getMetaData();

		String nombreColumna;
		List<T> registros = new ArrayList<T>();

		while (rs.next()) {

			T objeto = Reflexion.getInstancia(clazz);
			
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {

				nombreColumna = rsmd.getColumnLabel(i);
				Map<String, Class<?>> map = getMapeoTipos();
				Object valor =  rs.getObject(nombreColumna, map.get(rsmd.getColumnTypeName(i)));

				Reflexion.setValorMetodo(objeto, nombreColumna, valor);
			}

			registros.add(objeto);
		}

		return registros;
	}

	public static Map<String, Class<?>> getMapeoTipos() {

		Map<String, Class<?>> map = new HashMap<String, Class<?>>();

		map.put("char", String.class);
		map.put("nchar", String.class);
		map.put("varchar", String.class);
		map.put("nvarchar", String.class);
		map.put("ntext", String.class);
		map.put("uniqueidentifier", String.class);
		map.put("bit", Boolean.class);
		map.put("tinyint", Short.class);
		map.put("smallint", Short.class);
		map.put("int", Integer.class);
		map.put("real", Float.class);
		map.put("bigint", Long.class);
		map.put("float", Double.class);
		map.put("numeric", Double.class);
		map.put("decimal", Double.class);
		map.put("money", Double.class);
		map.put("smallmoney", Double.class);
		map.put("binary", byte[].class);
		map.put("varbinary", byte[].class);
		map.put("date", java.time.LocalDate.class);
		map.put("smalldatetime", java.time.LocalDateTime.class);
		map.put("datetime", java.time.LocalDateTime.class);
		map.put("datetime2", java.time.LocalDateTime.class);

		return map;
	}

	@Override
	public void close() throws SQLException {

		conexion.close();

	}

}