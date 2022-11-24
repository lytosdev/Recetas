package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import modelo.Receta;

public class PrevisualRecetaController implements Initializable {

    @FXML
    private ImageView imgFoto;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDescripcion;

    public PrevisualRecetaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void ver() {
        
        PrincipalController.gestorVistas.abrirVista("/vista/RecetaDetalle.fxml", "Detalle receta");
    }

    public void cargar(Receta receta) {
    
        //BackgroundPosition bgposition = new BackgroundPosition();
        //BackgroundSize bgsize = new BackgroundSize(20, 20, false, false, false, true);
        //BackgroundImage bgImage = new BackgroundImage(receta.getImagen(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        //Background bg = new Background(bgImage);
        
        imgFoto.setImage(receta.getImagen());
        lblTitulo.setText(receta.getTitulo());
        lblDescripcion.setText(receta.getDescripcion());

    }

}