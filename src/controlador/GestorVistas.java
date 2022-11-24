package controlador;

import java.io.IOException;
import java.lang.reflect.Constructor;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import tools.Reflexion;

class GestorVistas {

  private Pane componenteContenedor;
  private Text componenteTitulo;

  public GestorVistas(Pane componenteContenedor) {
    this.componenteContenedor = componenteContenedor;

  }

  public GestorVistas(Pane componenteContenedor, Text componenteTitulo) {
    this.componenteContenedor = componenteContenedor;
    this.componenteTitulo = componenteTitulo;

  }

  public PropsVista abrirVista(String uri) {

    // Ocultamos las vistas actualmente visible
    componenteContenedor.getChildren().forEach(x -> x.setVisible(false));

    return supoVista(uri, null);
  }

  public PropsVista abrirVista(String uri, String titulo) {

    // Ocultamos las vistas actualmente visible
    componenteContenedor.getChildren().forEach(x -> x.setVisible(false));

    return supoVista(uri, titulo);
  }

  public PropsVista supoVista(String uri) {
    return supoVista(uri, null);
  }

  public PropsVista supoVista(String uri, String titulo) {

    Node vista = buscarVista(uri);
    PropsVista propsVista;

    // Si la vista no existe la creamos
    if (vista == null) {
      vista = renderVista(uri, titulo);
      propsVista = (PropsVista) vista.getUserData();
    } else {
      propsVista = (PropsVista) vista.getUserData();
      titulo = propsVista.getTitulo();
    }

    if (componenteTitulo != null && titulo != null) {
      componenteTitulo.setText(titulo);
    }

    // Ponemos la vista dada en primera posición y la mostramos
    vista.toFront();
    vista.setVisible(true);

    return propsVista;
  }

  public void cerrarVista(String uriVista) {

    // Eliminamos la vista dada
    componenteContenedor.getChildren().remove(buscarVista(uriVista));

    // Mostramos la vista que corresponde por orden de apertura
    int totalVistas = componenteContenedor.getChildren().size();
    Node vista = componenteContenedor.getChildren().get(totalVistas - 1);
    vista.setVisible(true);

    PropsVista propsVista = (PropsVista) vista.getUserData();
    String titulo = propsVista.getTitulo();

    if (componenteTitulo != null && titulo != null) {
      componenteTitulo.setText(titulo);
    }

  }

  // public Text getComponenteTitulo() {
  // return componenteTitulo;
  // }

  // public PropsVista getVista(String uri) {

  // Node vista = buscarVista(uri);
  // PropsVista propsVista = (PropsVista) vista.getUserData();

  // return propsVista;
  // }

  private Node buscarVista(String uri) {

    FilteredList<Node> vistas = componenteContenedor.getChildren().filtered(x -> {
      PropsVista propsVista = (PropsVista) x.getUserData();
      return propsVista.getUri().equals(uri);
    });

    return vistas.size() == 0 ? null : vistas.get(0);
  }

  private Node renderVista(String uri, String titulo) {

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(uri));
    fxmlLoader.setControllerFactory((Class<?> clazz) -> getInstancia(clazz));

    Node vista = null;

    try {

      vista = fxmlLoader.load();

    } catch (IOException e) {
      e.printStackTrace();
    }

    vista.setUserData(new PropsVista(uri, titulo, fxmlLoader.getController()));

    AnchorPane.setTopAnchor(vista, 0.0);
    AnchorPane.setLeftAnchor(vista, 0.0);
    AnchorPane.setRightAnchor(vista, 0.0);
    AnchorPane.setBottomAnchor(vista, 0.0);
    componenteContenedor.getChildren().add(0, vista);

    return vista;
  }

  private Object getInstancia(Class<?> clazz) {

    try {

      // Busca un constructor con un parámetro de tipo 'ServicioVistas'
      for (Constructor<?> c : clazz.getConstructors()) {
        if (c.getParameterCount() == 1) {
          if (c.getParameterTypes()[0] == GestorVistas.class) {
            return c.newInstance(this);
          }
        }
      }

      // Si no existe un constructor con un parámetro de tipo
      // 'ServicioVistas' usamos el constructor por defecto
      return Reflexion.getInstancia(clazz);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public static Object[] cargarVista(String uri) {

    FXMLLoader fxmlLoader = new FXMLLoader(GestorVistas.class.getResource(uri));
    Node vista = null;

    try {
      vista = fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new Object[] { vista, fxmlLoader.getController() };
  }

  public static Object[] cargarVista(String uri, Callback<Class<?>, Object> controllerFactory) {

    FXMLLoader fxmlLoader = new FXMLLoader(GestorVistas.class.getResource(uri));
    fxmlLoader.setControllerFactory(controllerFactory);
    Node vista = null;

    try {
      vista = fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new Object[] { vista, fxmlLoader.getController() };
  }

}
