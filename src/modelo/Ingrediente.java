package modelo;

public class Ingrediente {

  private int cantidad;
  private String udMedida;
  private String nombre;

  public Ingrediente(int cantidad, String udMedida, String nombre) {
    this.cantidad = cantidad;
    this.udMedida = udMedida;
    this.nombre = nombre;
  }

  public int getCantidad() {
    return cantidad;
  }

  public String getUdMedida() {
    return udMedida;
  }

  public String getNombre() {
    return nombre;
  }

}
