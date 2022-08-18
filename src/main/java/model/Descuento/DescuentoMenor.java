package model.Descuento;

import model.Evento.Entrada;
import model.Usuario.Usuario;

public class DescuentoMenor implements Descuento {
    @Override
    public void calcularDescuento(Entrada entrada, Usuario usuario) {
        entrada.setDescuentoOtorgado(entrada.getDescuentoOtorgado()*0.25);
    }
}
