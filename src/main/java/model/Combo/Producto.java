package model.Combo;

import model.Usuario.Usuario;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id_producto;

    @Column(name = "tamanio")
    protected String tamanio;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Column(name = "precio")
    protected Float precio;


    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public abstract void calcularPrecio();

    public abstract void completarInformacion();
}
