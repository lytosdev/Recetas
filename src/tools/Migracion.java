package tools;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.image.Image;
import modelo.Categoria;
import modelo.CategoriaUso;
import modelo.Ingrediente;
import modelo.IngredienteUso;
import modelo.Paso;
import modelo.PasoUso;
import modelo.Receta;
import modelo.RecetaUso;
import modelo.UnidadMedida;
import modelo.UnidadMedidaUso;
import modelo.Utensilio;
import modelo.UtensilioUso;

public class Migracion {

  private List<Categoria> categorias;
  private List<Receta> recetas;
  private List<UnidadMedida> udsMedida;
  private List<Ingrediente> ingredientes;
  private List<Utensilio> utensilios;
  private List<Paso> pasos;

  public Migracion() {
    categorias = new ArrayList<>();
    recetas = new ArrayList<>();
    udsMedida = new ArrayList<>();
    ingredientes = new ArrayList<>();
    utensilios = new ArrayList<>();
    pasos = new ArrayList<>();

    leerJson();

  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public List<Receta> getRecetas() {
    return recetas;
  }

  public List<UnidadMedida> getUnidadesMedida() {
    return udsMedida;
  }

  public List<Ingrediente> getIngredientes() {
    return ingredientes;
  }

  public List<Utensilio> getUtensilios() {
    return utensilios;
  }

  public List<Paso> getPasos() {
    return pasos;
  }

  public void migrar() {

    // Insertamos categorías en la base de datos
    for (Categoria item : categorias) {
      CategoriaUso.insertar(item);
    }

    // Insertamos recetas en la base de datos
    for (Receta item : recetas) {
      RecetaUso.insertar(item);
    }

    // Insertamos unidades de medida en la base de datos
    for (UnidadMedida item : udsMedida) {
      UnidadMedidaUso.insertar(item);
    }

    // Insertamos ingredientes en la base de datos
    for (Ingrediente item : ingredientes) {
      IngredienteUso.insertar(item);
    }

    // Insertamos utensilios en la base de datos
    for (Utensilio item : utensilios) {
      UtensilioUso.insertar(item);
    }

    // Insertamos pasos en la base de datos
    for (Paso item : pasos) {
      PasoUso.insertar(item);
    }

  }

  private void leerJson() {

    List<String> recetasJson = null;
    try {

      recetasJson = Files.readAllLines(Paths.get("./tblRecetas.json"), StandardCharsets.UTF_8);
    } catch (Exception e) {
      // TODO: handle exception
    }
    String jsonString = recetasJson.stream().reduce((a, b) -> a + b).get();
    JSONArray arr = new JSONArray(jsonString);

    for (int i = 0; i < arr.length(); i++) {
      JSONObject jsonObj = arr.getJSONObject(i);

      String titulo = jsonObj.getString("titulo");
      String descripcion = jsonObj.getString("descripcion");
      String urlFoto = jsonObj.getString("imagen");
      int duracion = jsonObj.getInt("duracion");
      int personas = jsonObj.getInt("personas");
      String dificultad = jsonObj.getString("dificultad");
      String categoria = jsonObj.getString("categoria");

      int idReceta = i + 1;

      // Añadimos a ingredientes
      JSONArray arrIngredientes = jsonObj.getJSONArray("ingredientes");
      for (int j = 0; j < arrIngredientes.length(); j++) {
        JSONObject jsonIngrediente = arrIngredientes.getJSONObject(j);
        int cantidad = jsonIngrediente.getInt("cantidad");
        String udMedida = jsonIngrediente.getString("udMedida");
        String nombre = jsonIngrediente.getString("nombre");
        // Añadimos a unidades de medida
        int idUdMedida = setUdMedida(udMedida);
        ingredientes.add(new Ingrediente(nombre, cantidad, idReceta, idUdMedida));
      }

      // Añadimos a utensilios
      JSONArray arrUtensilios = jsonObj.getJSONArray("utensilios");
      for (int j = 0; j < arrUtensilios.length(); j++) {
        utensilios.add(new Utensilio(arrUtensilios.getString(j), idReceta));
      }

      // Añadimos a pasos
      JSONArray arrPasos = jsonObj.getJSONArray("pasos");
      for (int j = 0; j < arrPasos.length(); j++) {
        pasos.add(new Paso(arrPasos.getString(j), idReceta));
      }

      // Añadimos a categorías
      int idCategoria = setCategoria(categoria);

      // Añadimos a recetas
      Receta receta = new Receta(
          titulo,
          descripcion,
          Vista.getImage(urlFoto), 
          duracion,
          personas,
          Receta.Estado.PTE_VALIDAR.ordinal(),
          dificultad,
          LocalDate.now(),
          "admin",
          idCategoria);
      receta.setId(idReceta);
      recetas.add(receta);

    }

  }

  private int setUdMedida(String nombreUdMedida) {

    Optional<UnidadMedida> udMedidaTemp = udsMedida.stream()
        .filter(x -> x.getNombre().equalsIgnoreCase(nombreUdMedida)).findFirst();

    if (udMedidaTemp.isPresent()) {
      return udMedidaTemp.get().getId();
    }

    UnidadMedida udMedida = new UnidadMedida(nombreUdMedida.toLowerCase());
    udMedida.setId(udsMedida.size() + 1);
    udsMedida.add(udMedida);

    return udMedida.getId();
  }

  private int setCategoria(String nombreCategoria) {

    Optional<Categoria> categoriaTemp = categorias.stream()
        .filter(x -> x.getNombre().equalsIgnoreCase(nombreCategoria)).findFirst();

    if (categoriaTemp.isPresent()) {
      return categoriaTemp.get().getId();
    }

    Categoria categoria = new Categoria(nombreCategoria.toLowerCase());
    categoria.setId(categorias.size() + 1);
    categorias.add(categoria);

    return categoria.getId();
  }

}
