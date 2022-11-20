package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SubirRecetaController implements Initializable {

    @FXML
    private VBox pnlIngredientes;
    @FXML
    private VBox pnlPasos;

    public SubirRecetaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void nuevoPaso() {
        int totPasos = pnlPasos.getChildren().size();
        pnlPasos.getChildren().add(getPaso("Paso " + (totPasos + 1)));
    }

    @FXML
    private void nuevoIngrediente() {
        pnlIngredientes.getChildren().add(getIngrediente());
    }

    private AnchorPane getPaso(String texto) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Paso.fxml"));
        fxmlLoader.setControllerFactory((Class<?> clazz) -> new PasoController(this::borrarPaso));

        AnchorPane vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PasoController paso = fxmlLoader.getController();
        vista.setUserData(paso);
        paso.txtNumPaso.setText(texto);

        return vista;
    }

    private void borrarPaso(PasoController obj) {

        ObservableList<Node> pasos = pnlPasos.getChildren();
        Node pasoSelec = null;

        for (Node paso : pasos) {
            // Actualizamos el número de paso a partir del borrado
            if (pasoSelec != null) {
                int numPaso = pasos.indexOf(paso);
                PasoController pasoController = (PasoController) paso.getUserData();
                pasoController.txtNumPaso.setText("Paso " + numPaso);
            }
            // Guardamos el paso que queremos borrar
            if (paso.getUserData().equals(obj)) {
                pasoSelec = paso;
            }
        }

        pnlPasos.getChildren().remove(pasoSelec);

    }

    private AnchorPane getIngrediente() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Ingrediente.fxml"));
        fxmlLoader.setControllerFactory((Class<?> clazz) -> new IngredienteController(this::borrarIngrediente));

        AnchorPane vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        IngredienteController ingrediente = fxmlLoader.getController();
        vista.setUserData(ingrediente);

        return vista;
    }

    private void borrarIngrediente(IngredienteController obj) {

        ObservableList<Node> ingredientes = pnlIngredientes.getChildren();
        Node ingredienteSelec = null;

        for (Node ingrediente : ingredientes) {
            // Guardamos el ingrediente que queremos borrar
            if (ingrediente.getUserData().equals(obj)) {
                ingredienteSelec = ingrediente;
                break;
            }
        }

        pnlIngredientes.getChildren().remove(ingredienteSelec);

    }

}