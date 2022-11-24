package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Receta;
import modelo.RecetaUso;

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

    public RecetasController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos los botones correspondientes a las categorías
        for (String texto : nombresCat) {
            Callback<Class<?>, Object> factoryController = (Class<?> clazz) -> new BotonCategoriaController(this::abrir);
            Object[] arr = GestorVistas.cargarVista("/vista/BotonCategoria.fxml", factoryController);
            Object btnCat = arr[0];
            BotonCategoriaController botonController = (BotonCategoriaController) arr[1];
            botonController.txtTexto.setText(texto);
            pnlCategorias.getChildren().add((Button) btnCat);
        }

        abrirJson();

    }

    private void abrir(String texto) {

        for (int i = 0; i < nombresCat.length; i++) {
            if (nombresCat[i].equals(texto)) {
                txtResultados.setText(resultCat[i] + " recetas encontradas para la categoría " + texto);
                pnlResultados.getChildren().clear();
                for (int j = 0; j < resultCat[i]; j++) {
                    Object recetaNode = GestorVistas.cargarVista("/vista/PrevisualReceta.fxml")[0];
                    pnlResultados.getChildren().add((Node) recetaNode);
                }
            }
        }

    }

    private void abrirJson() {

        List<Receta> recetas = RecetaUso.getRecetasJson();

        txtResultados.setText(recetas.size() + " recetas encontradas");
        pnlResultados.getChildren().clear();

        for (Receta receta : recetas) {
            Object[] arr = GestorVistas.cargarVista("/vista/PrevisualReceta.fxml");
            Object recetaNode = arr[0];
            PrevisualRecetaController recetaController = (PrevisualRecetaController) arr[1];
            recetaController.cargar(receta);
            pnlResultados.getChildren().add((Node) recetaNode);
        }

    }

    public int getRnd() {
        return (int) Math.floor(Math.random() * (maxRnd - minRnd + 1)) + minRnd;
    }

}