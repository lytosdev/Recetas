package modelo;

public class Ingrediente {

  private int id;
  private String nombre;
  private int cantidad;
  private int idReceta;
  private int idUdMedida;

  public Ingrediente() {

  }

  public Ingrediente(String nombre, int cantidad, int idReceta, int idUdMedida) {
    this.nombre = nombre;
    this.cantidad = cantidad;
    this.idReceta = idReceta;
    this.idUdMedida = idUdMedida;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public int getIdReceta() {
    return idReceta;
  }

  public void setIdReceta(int idReceta) {
    this.idReceta = idReceta;
  }

  public int getIdUdMedida() {
    return idUdMedida;
  }

  public void setIdUdMedida(int idUdMedida) {
    this.idUdMedida = idUdMedida;
  }

}
