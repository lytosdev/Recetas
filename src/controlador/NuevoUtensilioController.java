package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import controlador.GestorErrores.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tools.Vista;

public class NuevoUtensilioController implements Initializable {

    @FXML
    private TextField inpUtensilio;
    @FXML
    private Label lblErrorUtensilio;

    private Consumer<Node> borrarUtensilio;
    private String utensilio;
    private GestorErrores gestorErrores;

    public NuevoUtensilioController(Consumer<Node> borrarUtensilio) {
        this.borrarUtensilio = borrarUtensilio;
    }

    public NuevoUtensilioController(Consumer<Node> borrarUtensilio, String utensilio) {
        this.borrarUtensilio = borrarUtensilio;
        this.utensilio = utensilio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        inpUtensilio.setText(utensilio);
        lblErrorUtensilio.setText("");
        
    }

    @FXML
    private void borrar(ActionEvent event) {

        desuscribirErrores();

        Node node = (Node) event.getSource();
        borrarUtensilio.accept(Vista.buscarPadre(node, "nuevoUtensilio"));
    }

    public void suscribirErrores(GestorErrores gestorErrores) {
        this.gestorErrores = gestorErrores;

        gestorErrores.suscribir(inpUtensilio, lblErrorUtensilio,
                new TipoError[] { TipoError.NO_VACIO, TipoError.SOLO_TEXTO, TipoError.LONGITUD }, 100);
    }

    private void desuscribirErrores() {
        if (gestorErrores != null) {
            gestorErrores.desuscribir(inpUtensilio);
        }
    }

}