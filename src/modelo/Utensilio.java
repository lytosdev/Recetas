package modelo;

public class Utensilio {

  private int id;
  private String nombre;
  private int idReceta;

  public Utensilio() {

  }

  public Utensilio(String nombre, int idReceta) {
    this.nombre = nombre;
    this.idReceta = idReceta;
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

  public int getIdReceta() {
    return idReceta;
  }

  public void setIdReceta(int idReceta) {
    this.idReceta = idReceta;
  }

}
