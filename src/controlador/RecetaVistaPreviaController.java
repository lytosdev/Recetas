package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import modelo.RecetaDetalle;

public class RecetaVistaPreviaController implements Initializable {

    @FXML
    private Pane pnlImagen;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDescripcion;

    public RecetaVistaPreviaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void ver() {
        
        PrincipalController.gestorVistas.abrirVista("/vista/RecetaDetalle.fxml", "Detalle receta");
    }

    public void cargar(RecetaDetalle recetaDetalle) {
    
        //BackgroundPosition bgposition = new BackgroundPosition();
        //BackgroundSize bgsize = new BackgroundSize();
        Background bg = new Background(new BackgroundImage(recetaDetalle.getImagen(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        
        pnlImagen.setBackground(bg);
        lblTitulo.setText(recetaDetalle.getTitulo());
        lblDescripcion.setText(recetaDetalle.getDescripcion());
    
    }

}