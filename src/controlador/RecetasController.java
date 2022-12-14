package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Categoria;
import modelo.Receta;
import modelo.RecetaUso;

public class RecetasController implements Initializable {

    @FXML
    private HBox pnlCategorias;
    @FXML
    private FlowPane pnlResultados;
    @FXML
    private Text txtResultados;

    public RecetasController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos los botones correspondientes a las categorías
        for (Categoria item : PrincipalController.categorias) {

            Callback<Class<?>, Object> factoryController = (Class<?> clazz) -> new BotonCategoriaController(item,
                    this::abrir);
            Object[] comp = GestorVistas.cargarVista("/vista/BotonCategoria.fxml", factoryController);
            Object compVista = comp[0];

            pnlCategorias.getChildren().add((Button) compVista);
        }
        
    }

    private void abrir(Categoria categoria) {

        List<Receta> recetas = RecetaUso.selectPorCategoria(categoria.getId()).getObjeto();

        txtResultados.setText(recetas.size() + " recetas encontradas para la categoría " + categoria.getNombre());

        pnlResultados.getChildren().clear();
        for (Receta item : recetas) {

            Callback<Class<?>, Object> factoryController = (Class<?> clazz) -> new PrevisualRecetaController(item);
            Object[] comp = GestorVistas.cargarVista("/vista/PrevisualReceta.fxml", factoryController);

            pnlResultados.getChildren().add((Node) comp[0]);
        }

    }

}