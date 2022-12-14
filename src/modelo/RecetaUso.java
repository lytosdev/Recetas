package modelo;

import java.sql.SQLException;
import java.util.List;

public class RecetaUso {

	static String insertQuery() {
		return "INSERT INTO Recetas "
				+ "(Titulo, Descripcion, Imagen, Duracion, Personas, Dificultad, Fecha, Autor, Estado, IdCategoria) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	static String selectPorCategoriaQuery() {
		return "SELECT Id, Titulo, Descripcion, Imagen, Duracion, Personas, Dificultad, Fecha, Autor, Estado, IdCategoria "
				+ "FROM Recetas "
				+ "WHERE IdCategoria=?";
	}

	static String selectPteValidarQuery() {
		return "SELECT Id, Titulo, Descripcion, Imagen, Duracion, Personas, Dificultad, Fecha, Autor, Estado, IdCategoria "
				+ "FROM Recetas "
				+ "WHERE Estado='" + Receta.Estado.PTE_VALIDAR.ordinal() + "'";
	}

	static String selectValidasQuery() {
		return "SELECT Id, Titulo, Descripcion, Imagen, Duracion, Personas, Dificultad, Fecha, Autor, Estado, IdCategoria "
				+ "FROM Recetas "
				+ "WHERE Estado='" + Receta.Estado.VALIDA.ordinal() + "'";
	}

	public static RespuestaUso<?> insertar(Receta receta) {

		try (RepoGenerico rg = new RepoGenerico()) {

			int[] resul = rg.insert(insertQuery(), new Object[] {
					receta.getTitulo(),
					receta.getDescripcion(),
					receta.getImagen(),
					receta.getDuracion(),
					receta.getPersonas(),
					receta.getDificultad(),
					receta.getFecha(),
					receta.getAutor(),
					receta.getEstado(),
					receta.getIdCategoria()
			});

			receta.setId(resul[1]);

			return new RespuestaUso<>("Receta insertada correctamente", RespuestaUso.EXITO);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al insertar la receta", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Receta>> selectPorCategoria(int idCategoria) {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Receta> resul = rg.getPor(selectPorCategoriaQuery(), new Object[] { idCategoria }, Receta.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Receta>> selectPteValidar() {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Receta> resul = rg.getPor(selectPteValidarQuery(), null, Receta.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Receta>> selectValidas() {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Receta> resul = rg.getPor(selectValidasQuery(), null, Receta.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

}
