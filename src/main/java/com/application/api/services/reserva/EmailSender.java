package com.application.api.services.reserva;

import com.application.api.services.interfaces.IEmailSender;
import com.application.api.vo.ReservaVOPartidoVO;
import com.application.api.vo.ReservaVOwithPeliculaVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSender implements IEmailSender {
    @Value("${spring.mail.username}")
    private String SENDER_ADDRESS ;

    @Autowired
    private JavaMailSender mailSender;

    public void EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailWithPelicula(String email, ReservaVOwithPeliculaVO reservaVOwithPeliculaVO) throws MessagingException {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(SENDER_ADDRESS);
        message.setTo("ebloemercorrea@frba.utn.edu.ar");
        //message.setTo("ruthlazon@gmail.com");
        message.setText("Cliente: Ruth Lazon Aguilar"+
        //message.setText("Cliente: Eliel Bloemer "+
                        "\n Reserva number :"+reservaVOwithPeliculaVO.getIdReserva()+" \n Cantidad de Reservas: "+ reservaVOwithPeliculaVO.getCantidadEntradas()+
                        "\n Descuento Otorgado $ "+reservaVOwithPeliculaVO.descuentoOtorgado+"\n Tipo de descuento: "+reservaVOwithPeliculaVO.tipoDeDescuento+
                        "\n Pelicula: "+reservaVOwithPeliculaVO.pelicula.nombrePelicula+"\n Sala: "+reservaVOwithPeliculaVO.pelicula.identificacionSala+
                        "\n Precio $ "+reservaVOwithPeliculaVO.pelicula.precioEvento+"\n Fecha: "+reservaVOwithPeliculaVO.pelicula.fechaEvento+
                        "\n Costo Total $ "+reservaVOwithPeliculaVO.costoTotal + "\n Muchas gracias por elegirnos.");
        message.setSubject("Reserva CineMachine.");
        mailSender.send(message);
    }

    @Override
    public void sendEmailWithPartido(String email, ReservaVOPartidoVO reservaVOPartidoVO) throws MessagingException {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(SENDER_ADDRESS);
        //message.setTo("ruthlazon@gmail.com");
        //message.setTo("ebloemercorrea@frba.utn.edu.ar","eliveltonbloemer@gmail.com","ruthlazon@gmail.com");
        //message.setTo("eliveltonbloemer@gmail.com");
        message.setTo("ebloemercorrea@frba.utn.edu.ar");
        message.setText("Cliente: Elivelton Bloemer Correa"+
                "\n Reserva number :"+reservaVOPartidoVO.getIdReserva()+" \n Cantidad de Reservas: "+ reservaVOPartidoVO.getCantidadEntradas()+
                "\n Descuento Otorgado $ "+reservaVOPartidoVO.descuentoOtorgado+"\n Tipo de descuento: "+reservaVOPartidoVO.tipoDeDescuento+
                "\n Partido: "+ reservaVOPartidoVO.partido.nombreSeleccionA + " X "+ reservaVOPartidoVO.partido.nombreSeleccionB+
                "\n Sala: "+reservaVOPartidoVO.partido.getIdSala()+
                "\n Precio $ "+reservaVOPartidoVO.partido.precioEvento+"\n Fecha: "+reservaVOPartidoVO.partido.fechaEvento+
                "\n Costo Total $ "+reservaVOPartidoVO.costoTotal + "\n Muchas gracias por elegirnos.");
        message.setSubject("Reserva CineMachine.");
        mailSender.send(message);
    }
}