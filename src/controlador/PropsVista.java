package controlador;

import java.util.Objects;

class PropsVista {

  private String uri;
  private String titulo;
  private Object controlador;

  public PropsVista(String uri, String titulo, Object controlador) {
    this.uri = uri;
    this.titulo = titulo;
    this.controlador = controlador;

  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Object getControlador() {
    return controlador;
  }

  public void setControlador(Object controlador) {
    this.controlador = controlador;
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;

    if (!(obj instanceof PropsVista))
      return false;

    PropsVista propsVista = (PropsVista) obj;

    return uri.equals(propsVista.uri);
  }

}
