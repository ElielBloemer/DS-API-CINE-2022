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
        message.setText("Reserva number :"+reservaVOwithPeliculaVO.getIdReserva());
        message.setSubject("Reserva Cine");
        mailSender.send(message);
        /*MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        messageHelper.setFrom(SENDER_ADDRESS);
        messageHelper.setTo("ebloemercorrea@frba.utn.edu.ar");
        messageHelper.setSubject("Reserva Cine");
        messageHelper.setText(reservaVOwithPeliculaVO.toString(), true);
        mailSender.send(mimeMessage);*/


    }

    @Override
    public void sendEmailWithPartido(String email, ReservaVOPartidoVO reservaVOPartidoVO) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        messageHelper.setFrom(SENDER_ADDRESS);
        messageHelper.setTo("ebloemercorrea@frba.utn.edu.ar");
        messageHelper.setSubject("Reserva Cine");

        boolean useHtml = true;
        messageHelper.setText(String.valueOf(reservaVOPartidoVO), useHtml);

        mailSender.send(mimeMessage);

    }
}