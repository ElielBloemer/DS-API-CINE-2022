package model.Combo;


import javax.persistence.*;

@Entity
@DiscriminatorValue(value="bebida")
public class Bebida extends Producto {

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Override
    public void calcularPrecio() {

    }

    public void setPrecio() {
        this.precio = 150f;
    }

    @Override
    public void completarInformacion() {

    }
}
