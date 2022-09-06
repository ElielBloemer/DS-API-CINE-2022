package com.application.api.services.reserva;

import com.application.api.model.evento.Evento;
import com.application.api.model.reserva.Reserva;
import com.application.api.model.usuario.Usuario;
import com.application.api.persistance.ReservaRepository;
import com.application.api.services.descuentos.Descuento;
import com.application.api.services.descuentos.DescuentoBasicoService;
import com.application.api.services.descuentos.DescuentoEstandarService;
import com.application.api.services.descuentos.DescuentoPremiumService;
import com.application.api.services.interfaces.IAccountService;
import com.application.api.services.interfaces.IReservaService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.ReservaVO;
import com.application.api.vo.ReservaVOPartidoVO;
import com.application.api.vo.ReservaVOwithPeliculaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.Des;

import javax.mail.MessagingException;

@Service
public class ReservaService implements IReservaService, IAccountService {

    private ReservaRepository reservaRepository;

    private final Validacion validacion=new Validacion();
    private EmailSender emailSender;

    @Autowired
    public void setEmailSender(EmailSender emailSender){
        this.emailSender=emailSender;
    }

    @Autowired
    public void setReservaRepository(ReservaRepository reservaRepository){
        this.reservaRepository=reservaRepository;
    }

    @Override
    public Reserva saveReserva(ReservaVO reservaVO) {
        return saveReserva(reservaVO.idReserva,reservaVO.cantidadEntradas,reservaVO.evento,reservaVO.descuentoOtorgado,reservaVO.tipoDeDescuento,reservaVO.reservaActiva,reservaVO.costoTotal);
    }

    @Override
    public Reserva saveReserva(String idReserva, Integer cantEntradas, Evento evento, Float descuentoOtorgado, String tipoDeDescuento, boolean reservaActiva,float costoTotal) {
        Reserva reserva=getReservaById(idReserva.toUpperCase());
        validacion.getValidacionSiEstaEnElSistema(reserva,idReserva,"Reserva YA existente en el sistema.");
        return reservaRepository.save(new Reserva(generarIdReserva(),cantEntradas,evento,null,descuentoOtorgado,tipoDeDescuento,reservaActiva,costoTotal));
    }

    @Override
    public Reserva getReservaById(String idReserva) {
        return reservaRepository.findByIdReserva(idReserva);
    }

    @Override
    public float calcularPrecioReserva(Usuario usuario,Integer descuentoOtorgado, Evento evento,Integer cantidadDeEntradas) {
        //reserva.setCostoTotal((evento.getPrecio()*cantidadDeEntradas)-(reserva.getDescuentoOtorgado()*cantidadDeEntradas));
        return (evento.getPrecio()*cantidadDeEntradas)-(descuentoOtorgado*cantidadDeEntradas);
    }

    //Me Devuelve el valor que voy a descontar de UNA unica entrada de ahi solo multiplo por el valor de las demas.
    @Override
    public float descuentoAOtorgar(Usuario usuario, Evento eventoElegido, Descuento descuento) {
        return descuento.calcularDescuento(usuario,eventoElegido);
    }

    //TODO ACA DEVO MANDAR UNA RESERVA NO NULA!!!!!!!!!!!!
    // ACA SETEO EL DESCUENTO QUE NECESITO PARA CALCULAR EL PRECIO
    @Override
    public ReservaVO obtenerDescuento(ReservaVO reservaVO,Usuario usuario, Evento evento) {
        Descuento descuentoBasico= new DescuentoBasicoService("Descuento Basico");
        Descuento descuentoEstandar= new DescuentoEstandarService("Descuento Estandar");
        Descuento descuentoPremiun= new DescuentoPremiumService("Descuento Premiun");

        if(descuentoEstandar.esValido(usuario,evento)){
            reservaVO.setDescuentoOtorgado(descuentoAOtorgar(usuario,evento,descuentoEstandar));
            reservaVO.setTipoDeDescuento(descuentoEstandar.getNombreDescuento().toUpperCase());
            return reservaVO;
        } else if ((descuentoPremiun.esValido(usuario,evento))) {
            reservaVO.setDescuentoOtorgado(descuentoAOtorgar(usuario,evento,descuentoEstandar));
            reservaVO.setTipoDeDescuento(descuentoPremiun.getNombreDescuento().toUpperCase());
            return reservaVO;
        }else {
        reservaVO.setDescuentoOtorgado(descuentoAOtorgar(usuario,evento,descuentoBasico));
        reservaVO.setTipoDeDescuento(descuentoBasico.getNombreDescuento().toUpperCase());
        return reservaVO;
        }
    }

    @Override
    public String generarIdReserva() {
        String idReserva = "";
        //idReserva = "";
        int a;
        String CaracteresNoDeseados = "AEIOU";
        for (int i = 0; i < 10; i++) {
            if (i < 5) {    // 0,1,2,3,4 posiciones de numeros
                idReserva = (int) (Math.random() * 9) + "" + idReserva;
            } else {       // 5,6,7,8,9 posiciones de letras
                do {
                    a = (int) (Math.random() * 26 + 65);///
                } while (CaracteresNoDeseados.indexOf(a) >= 0);

                char letra = (char) a;
                if (i == 5) {
                    idReserva = idReserva + letra;
                } else {
                    idReserva = idReserva + letra;
                }
            }
        }
        return idReserva;
    }

    @Override
    public void sendEmailWithPelicula(String email, ReservaVOwithPeliculaVO reservaVOwithPeliculaVO) throws MessagingException {
        emailSender.sendEmailWithPelicula("",reservaVOwithPeliculaVO);
    }

    @Override
    public void sendEmailWithPartido(String email, ReservaVOPartidoVO reservaVOPartidoVO) throws MessagingException {
        emailSender.sendEmailWithPartido("",reservaVOPartidoVO);
    }
}
