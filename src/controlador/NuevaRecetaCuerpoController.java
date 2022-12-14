package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modelo.Ingrediente;
import modelo.Paso;
import modelo.Utensilio;

public class NuevaRecetaCuerpoController implements Initializable {

    @FXML
    private VBox pnlIngredientes;
    @FXML
    private VBox pnlUtensilios;
    @FXML
    private VBox pnlPasos;

    private GestorErrores gestorErrores;

    public NuevaRecetaCuerpoController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void nuevoPaso() {

        int totPasos = pnlPasos.getChildren().size();

        Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoPasoController(this::borrarPaso,
                totPasos + 1);
        Object[] comp = GestorVistas.cargarVista("/vista/NuevoPaso.fxml", factory);

        Node node = (Node) comp[0];
        NuevoPasoController nuevoPaso = (NuevoPasoController) comp[1];
        node.setUserData(nuevoPaso);
        
        if (gestorErrores != null) {
            nuevoPaso.suscribirErrores(gestorErrores);
        }

        pnlPasos.getChildren().add(node);
    }

    @FXML
    private void nuevoIngrediente() {

        Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoIngredienteController(
                this::borrarIngrediente);
        Object[] comp = GestorVistas.cargarVista("/vista/NuevoIngrediente.fxml", factory);

        Node node = (Node) comp[0];
        NuevoIngredienteController nuevoIngrediente = (NuevoIngredienteController) comp[1];

        if (gestorErrores != null) {
            nuevoIngrediente.suscribirErrores(gestorErrores);
        }

        pnlIngredientes.getChildren().add(node);
    }

    @FXML
    private void nuevoUtensilio() {

        Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoUtensilioController(this::borrarUtensilio);
        Object[] comp = GestorVistas.cargarVista("/vista/NuevoUtensilio.fxml", factory);

        Node node = (Node) comp[0];
        NuevoUtensilioController nuevoUtensilio = (NuevoUtensilioController) comp[1];

        if (gestorErrores != null) {
            nuevoUtensilio.suscribirErrores(gestorErrores);
        }

        pnlUtensilios.getChildren().add(node);
    }

    public void suscribirErrores(GestorErrores gestorErrores) {
        this.gestorErrores = gestorErrores;
    }

    public void setRecetaCuerpo(List<Ingrediente> ingredientes, List<Utensilio> utensilios, List<Paso> pasos) {

        // Reseteamos
        pnlIngredientes.getChildren().clear();
        pnlUtensilios.getChildren().clear();
        pnlPasos.getChildren().clear();

        // Cargamos ingredientes
        for (Ingrediente item : ingredientes) {

            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoIngredienteController(
                    this::borrarIngrediente, item);
            String uri = "/vista/NuevoIngrediente.fxml";
            Object[] comp = GestorVistas.cargarVista(uri, factory);

            pnlIngredientes.getChildren().add((Node) comp[0]);
        }

        // Cargamos utensilios
        for (Utensilio item : utensilios) {

            String uri = "/vista/NuevoUtensilio.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoUtensilioController(this::borrarUtensilio,
                    item);
            Object[] comp = GestorVistas.cargarVista(uri, factory);

            pnlUtensilios.getChildren().add((Node) comp[0]);
        }

        // Cargamos pasos
        for (Paso item : pasos) {

            int numPaso = pasos.indexOf(item) + 1;

            String uri = "/vista/NuevoPaso.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoPasoController(this::borrarPaso, numPaso,
                    item);
            Object[] comp = GestorVistas.cargarVista(uri, factory);

            NuevoPasoController nuevoPaso = (NuevoPasoController) comp[1];
            Node node = (Node) comp[0];
            node.setUserData(nuevoPaso);

            pnlPasos.getChildren().add(node);
        }

    }

    private void borrarPaso(Node node) {

        ObservableList<Node> pasos = pnlPasos.getChildren();
        boolean encontrado = false;

        for (Node paso : pasos) {
            // Actualizamos el número de paso a partir del borrado
            if (encontrado) {
                int numPaso = pasos.indexOf(paso);
                NuevoPasoController pasoController = (NuevoPasoController) paso.getUserData();
                pasoController.setPaso(numPaso);
            }
            if (paso.equals(node)) {
                encontrado = true;
            }
        }

        pnlPasos.getChildren().remove(node);
    }

    private void borrarIngrediente(Node node) {
        pnlIngredientes.getChildren().remove(node);
    }

    private void borrarUtensilio(Node node) {
        pnlUtensilios.getChildren().remove(node);
    }

}