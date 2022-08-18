package model.Combo;

import javax.persistence.*;
import java.util.Scanner;


@Entity
@DiscriminatorValue(value="baldeDePochoclo")
public class BaldePochoclo extends Producto {

    @Column(name="edicion")
    private String edicion;

    @Override
    public void calcularPrecio() {

    }

    public void setPrecio() {
        this.precio = 400f;
    }

    @Override
    public void completarInformacion() {
        String entrada;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese tamanio del balde: ");
        entrada = scanner.nextLine();

        System.out.print("Ingrese el precio");
    }

}
