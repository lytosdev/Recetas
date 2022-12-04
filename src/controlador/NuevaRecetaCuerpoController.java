package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modelo.Ingrediente;

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
        Object[] arr = GestorVistas.cargarVista("/vista/NuevoPaso.fxml", factory);

        Node node = (Node) arr[0];
        NuevoPasoController pasoController = (NuevoPasoController) arr[1];

        node.setUserData(pasoController);
        if (gestorErrores != null) {
            pasoController.suscribirErrores(gestorErrores);
        }

        pnlPasos.getChildren().add(node);
    }

    @FXML
    private void nuevoIngrediente() {

        Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoIngredienteController(
                this::borrarIngrediente);
        Object[] arr = GestorVistas.cargarVista("/vista/NuevoIngrediente.fxml", factory);

        Node node = (Node) arr[0];
        NuevoIngredienteController ingredienteController = (NuevoIngredienteController) arr[1];

        if (gestorErrores != null) {
            ingredienteController.suscribirErrores(gestorErrores);
        }

        pnlIngredientes.getChildren().add(node);
    }

    @FXML
    private void nuevoUtensilio() {

        Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoUtensilioController(this::borrarUtensilio);
        Object[] arr = GestorVistas.cargarVista("/vista/NuevoUtensilio.fxml", factory);

        Node node = (Node) arr[0];
        NuevoUtensilioController utensilioController = (NuevoUtensilioController) arr[1];

        if (gestorErrores != null) {
            utensilioController.suscribirErrores(gestorErrores);
        }

        pnlUtensilios.getChildren().add(node);
    }

    public void suscribirErrores(GestorErrores gestorErrores) {
        this.gestorErrores = gestorErrores;
    }

    public void cargar(Ingrediente[] ingredientes, String[] utensilios, String[] pasos) {

        // Cargamos ingredientes
        for (int i = 0; i < ingredientes.length; i++) {
            Ingrediente ing = ingredientes[i];

            int cantidad = ing.getCantidad();
            String udMedida = ing.getUdMedida();
            String nombre = ing.getNombre();

            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoIngredienteController(
                    this::borrarIngrediente, cantidad, udMedida, nombre);
            String uri = "/vista/NuevoIngrediente.fxml";
            Object[] arrIngrediente = GestorVistas.cargarVista(uri, factory);

            pnlIngredientes.getChildren().add((Node) arrIngrediente[0]);
        }

        // Cargamos utensilios
        for (int i = 0; i < utensilios.length; i++) {

            String txtUtensilio = utensilios[i];
            String uri = "/vista/NuevoUtensilio.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoUtensilioController(this::borrarUtensilio,
                    txtUtensilio);
            Object[] arrUtensilio = GestorVistas.cargarVista(uri, factory);

            pnlUtensilios.getChildren().add((Node) arrUtensilio[0]);
        }

        // Cargamos pasos
        for (int i = 0; i < pasos.length; i++) {

            int numPaso = i + 1;
            String txtPaso = pasos[i];
            String uri = "/vista/NuevoPaso.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new NuevoPasoController(this::borrarPaso, numPaso,
                    txtPaso);
            Object[] arrPaso = GestorVistas.cargarVista(uri, factory);

            NuevoPasoController pasoController = (NuevoPasoController) arrPaso[1];
            Node node = (Node) arrPaso[0];
            node.setUserData(pasoController);

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