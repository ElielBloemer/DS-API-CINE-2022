package model.Descuento;

import model.Evento.Entrada;
import model.Usuario.Usuario;

public class DescuentoMedio implements Descuento {

    @Override
    public void calcularDescuento(Entrada entrada, Usuario usuario) {
        if(!usuario.esMayorDeEdad()){
             entrada.setDescuentoOtorgado(entrada.getPrecio() * 0.5);
        } else{
            entrada.setDescuentoOtorgado(0);
        }
    }
}
