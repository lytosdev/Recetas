package modelo;

import java.sql.SQLException;
import java.util.List;

public class CategoriaUso {

	static String insertQuery() {
		return "INSERT INTO Categorias "
				+ "(Nombre) "
				+ "VALUES (?)";
	}

	static String selectQuery() {
		return "SELECT Id, Nombre "
				+ "FROM Categorias";
	}

	public static RespuestaUso<?> insertar(Categoria categoria) {

		try (RepoGenerico rg = new RepoGenerico()) {

			int[] resul = rg.insert(insertQuery(), new Object[] {
					categoria.getNombre()
			});

			categoria.setId(resul[1]);

			return new RespuestaUso<>("Categoría insertada correctamente", RespuestaUso.EXITO);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al insertar la categoría", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Categoria>> select() {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Categoria> resul = rg.getPor(selectQuery(), null, Categoria.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

}
