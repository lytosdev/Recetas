package modelo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.image.Image;

public class RecetaUso {

  public static List<Receta> getRecetasJson() {

    List<String> recetasJson = null;
    try {
      recetasJson = Files.readAllLines(Paths.get("./prueba.json"), StandardCharsets.UTF_8);
    } catch (Exception e) {
      // TODO: handle exception
    }
    String jsonString = recetasJson.stream().reduce((a, b) -> a + b).get();
    JSONArray arr = new JSONArray(jsonString);

    List<Receta> recetas = new ArrayList<>();

    for (int i = 0; i < arr.length(); i++) {
      JSONObject jsonObj = arr.getJSONObject(i);

      String imagen = jsonObj.getString("imagen");
      String titulo = jsonObj.getString("titulo");
      String descripcion = jsonObj.getString("descripcion");
      String categoria = jsonObj.getString("categoria");
      String dificultad = jsonObj.getString("dificultad");
      int duracion = jsonObj.getInt("duracion");
      int personas = jsonObj.getInt("personas");

      JSONArray arrIngredientes = jsonObj.getJSONArray("ingredientes");
      List<Ingrediente> ingredientes = new ArrayList<>();
      for (int j = 0; j < arrIngredientes.length(); j++) {
        JSONObject jsonIngrediente = arrIngredientes.getJSONObject(j);

        int cantidad = jsonIngrediente.getInt("cantidad");
        String udMedida = jsonIngrediente.getString("udMedida");
        String nombre = jsonIngrediente.getString("nombre");
        ingredientes.add(new Ingrediente(cantidad, udMedida, nombre));
      }

      JSONArray arrUtensilios = jsonObj.getJSONArray("utensilios");
      List<String> utensilios = new ArrayList<>();
      for (int j = 0; j < arrUtensilios.length(); j++) {
        utensilios.add(arrUtensilios.getString(j));
      }

      JSONArray arrPasos = jsonObj.getJSONArray("pasos");
      List<String> pasos = new ArrayList<>();
      for (int j = 0; j < arrPasos.length(); j++) {
        pasos.add(arrPasos.getString(j));
      }

      Ingrediente[] newArrIngredientes = new Ingrediente[ingredientes.size()];
      newArrIngredientes = ingredientes.toArray(newArrIngredientes);
      Image img = new Image(imagen, 250*2, 180*2, true, true);
      recetas.add(new Receta(img, titulo, descripcion, categoria, dificultad, duracion, personas, newArrIngredientes,
          null, null));
    }

    return recetas;
  }

}
