package controlador;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import modelo.Ingrediente;
import modelo.RecetaDetalle;

public class RecetasController implements Initializable {

    @FXML
    private HBox pnlCategorias;
    @FXML
    private FlowPane pnlResultados;
    @FXML
    private Text txtResultados;

    private int minRnd = 1, maxRnd = 100;

    private String[] nombresCat = { "Carnes", "Pescados", "Postres", "Guisos", "Fritos", "Comida rápida", "Pizza",
            "Italiana", "Española", "Turca" };
    private int[] resultCat = { getRnd(), getRnd(), getRnd(), getRnd(), getRnd(), getRnd(), getRnd(), getRnd(),
            getRnd(), getRnd() };

    public RecetasController() throws IOException {

        // List<String> prueba = Files.readAllLines(Paths.get("./prueba.json"),
        // StandardCharsets.UTF_8);
        // String jsonString = prueba.stream().reduce((a, b) -> a + b).get();
        // JSONArray arr = new JSONArray(jsonString);
        // System.out.println(arr.length());
        // for (int i = 0; i < arr.length(); i++) {
        // String nom = arr.getJSONObject(i).getString("nombre");
        // System.out.println(nom);
        // }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos los botones correspondientes a las categorías
        for (String texto : nombresCat) {
            Button btnCat = getCategoria(texto);
            pnlCategorias.getChildren().add(btnCat);
        }

    }

    private void abrir(String texto) {

        for (int i = 0; i < nombresCat.length; i++) {
            if (nombresCat[i].equals(texto)) {
                txtResultados.setText(resultCat[i] + " recetas encontradas para la categoría " + texto);
                pnlResultados.getChildren().clear();
                for (int j = 0; j < resultCat[i]; j++) {
                    pnlResultados.getChildren().add(getRecetaVistaPrevia());
                }
            }
        }

    }

    private void abrirJson() {

        List<String> recetasJson = null;
        try {
            recetasJson = Files.readAllLines(Paths.get("./prueba.json"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // TODO: handle exception
        }

        String jsonString = recetasJson.stream().reduce((a, b) -> a + b).get();

        JSONArray arr = new JSONArray(jsonString);

        List<RecetaDetalle> recetasDetalle = new ArrayList<>();

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

            recetasDetalle
                    .add(new RecetaDetalle(new Image(imagen), titulo, descripcion, categoria, dificultad, duracion,
                            personas, null, null, null));
        }
        
        //receta.cargar(null);
    
    }

    private Button getCategoria(String texto) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/BotonCategoria.fxml"));
        fxmlLoader.setControllerFactory((Class<?> clazz) -> new BotonCategoriaController(this::abrir));

        Button vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BotonCategoriaController btnController = fxmlLoader.getController();
        btnController.texto.setText(texto);

        return vista;
    }

    private Node getRecetaVistaPrevia() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/RecetaVistaPrevia.fxml"));
        Node vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vista;
    }

    public int getRnd() {
        return (int) Math.floor(Math.random() * (maxRnd - minRnd + 1)) + minRnd;
    }

}