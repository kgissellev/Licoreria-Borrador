package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String nombre;

    @NotNull(message = "Debes especificar el código")
    @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50")
    private String codigo;

    @NotNull(message = "Debes especificar el precio")
    @Min(value = 0, message = "El precio mínimo es 0")
    private Float precio;

    public Productos(String nombre, String codigo, Float precio, Integer id) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.id = id;
    }

    public Productos(@NotNull(message = "Debes especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo) {
        this.codigo = codigo;
    }

    public Productos() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
