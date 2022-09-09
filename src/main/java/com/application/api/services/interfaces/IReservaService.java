package com.application.api.services.interfaces;

import com.application.api.model.evento.Evento;
import com.application.api.model.reserva.Reserva;
import com.application.api.model.usuario.Usuario;
import com.application.api.services.descuentos.Descuento;
import com.application.api.vo.ReservaVO;

public interface IReservaService {

    //TODO Guarda una reserva
    public Reserva saveReserva(ReservaVO reservaVO);

    public Reserva saveReserva(String idReserva, Integer cantEntradas, Evento evento,Float descuentoOtorgado, String tipoDeDescuento, boolean reservaActiva,float costoTotal);

    public Reserva getReservaById(String idReserva);

    public float calcularPrecioReserva(Usuario usuario,Integer descuentoOtorgado, Evento evento,Integer cantidadDeEntradas);

   // public boolean tieneDescuento(Evento evento, Usuario usuario, Descuento descuento);

    float descuentoAOtorgar(Usuario usuario, Evento eventoElegido, Descuento descuento);

    public ReservaVO obtenerDescuento(ReservaVO reserva,Usuario usuario, Evento evento,Integer cantidadDeEntradas);
    public String generarIdReserva();

}
