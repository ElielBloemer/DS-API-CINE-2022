package model.Descuento;

import model.Evento.Entrada;
import model.Usuario.Usuario;

public interface Descuento {
    void calcularDescuento(Entrada entrada, Usuario usuario);
}
