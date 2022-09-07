package com.application.api.controllers;


import com.application.api.model.evento.*;
import com.application.api.model.reserva.Reserva;
import com.application.api.services.interfaces.IPartidoService;
import com.application.api.services.interfaces.IPeliculaService;
import com.application.api.services.interfaces.IReservaService;
import com.application.api.services.interfaces.ISalaService;
import com.application.api.services.reserva.EmailSender;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController // essa clase vai receber as requisicoes HTTP
@RequestMapping("/api/V1.0/disenioDeSistemas/reserva")
@Tag(name = "Reserva controller",description = "CONTROLLER CRIADO PARA MANEJAR LAS RESERVAS")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class ReservaController {

    private final IReservaService iReservaService;

    private final IPeliculaService iPeliculaService;

    private final IPartidoService iPartidoService;

    private final ISalaService iSalaService;
    private final Validacion validacion = new Validacion();

    private EmailSender emailSender;

    @Autowired
    public void setEmailSender(EmailSender emailSender){
        this.emailSender=emailSender;
    }


    public ReservaController(IReservaService iReservaService,IPeliculaService iPeliculaService,IPartidoService iPartidoService,ISalaService iSalaService) {
        this.iReservaService=iReservaService;
        this.iPeliculaService=iPeliculaService;
        this.iPartidoService=iPartidoService;
        this.iSalaService=iSalaService;
    }

    //TODO aca USUARIO esta hardcodeado,OJOOO!!!
    @PostMapping("/withFootballMatch")
    @Operation(summary = "to create a Reserva with football match")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "criado")
    })
    public ResponseEntity<Object> guardarUnaReservaConPartido(@RequestParam Integer cantidadEntradas,Integer idPartido) throws MessagingException {
        Partido partido=iPartidoService.getPartidoById(idPartido);
        Sala sala=iSalaService.getSalaByIdentificacion(partido.getIdSala());
        for(int i=0;i<cantidadEntradas;i++){
            iSalaService.uptadeSalaByIdenficacionforSale(sala.getIdentificacionSala());
        }
        ReservaVO reservaVO=new ReservaVO();
        PartidoVO newPartido=new PartidoVO(partido);
        reservaVO.setIdReserva(iReservaService.generarIdReserva());
        reservaVO.setEvento(partido);
        reservaVO.setCantidadEntradas(cantidadEntradas);
        //TODO aca debo pasar el usuario han!NO el null.
        iReservaService.obtenerDescuento(reservaVO,null,partido);
        reservaVO.setReservaActiva(true);
        reservaVO.setCostoTotal(newPartido.getPrecioEvento()*cantidadEntradas - (reservaVO.getDescuentoOtorgado()*cantidadEntradas));
        Reserva response= iReservaService.saveReserva(reservaVO);
        ReservaVOPartidoVO newReserva=new ReservaVOPartidoVO (response,newPartido);
        //newReserva.setEvento(partido);
        emailSender.sendEmailWithPartido("",newReserva);
        return new ResponseEntity<>(newReserva, HttpStatus.CREATED);
    }

    //TODO aca USUARIO esta hardcodeado,OJOOO!!!
    @PostMapping("/withFilm")
    @Operation(summary = "to create a Reserva with film")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "criado")
    })
    public ResponseEntity<Object> guardarUnaReservaConPelicula(@RequestParam Integer cantidadEntradas,String nombrePelicula) throws MessagingException {
        //Partido partido=iPartidoService.getPartidoById(idPartido);
        Pelicula pelicula=iPeliculaService.getPeliculaByName(nombrePelicula);
        Sala sala=iSalaService.getSalaByIdentificacion(pelicula.getIdSala());
        for(int i=0;i<cantidadEntradas;i++){
            iSalaService.uptadeSalaByIdenficacionforSale(sala.getIdentificacionSala());
        }
        ReservaVO reservaVO=new ReservaVO();
        PeliculaVO newPelicula=new PeliculaVO(pelicula);
        reservaVO.setIdReserva(iReservaService.generarIdReserva());
        reservaVO.setEvento(pelicula);
        reservaVO.setCantidadEntradas(cantidadEntradas);
        //TODO aca debo pasar el usuario han!NO el null.
        iReservaService.obtenerDescuento(reservaVO,null,pelicula);
        reservaVO.setReservaActiva(true);
        reservaVO.setCostoTotal(newPelicula.getPrecioEvento()*cantidadEntradas - (reservaVO.getDescuentoOtorgado()*cantidadEntradas));
        Reserva response= iReservaService.saveReserva(reservaVO);
        ReservaVOwithPeliculaVO newReserva=new ReservaVOwithPeliculaVO (response,newPelicula);
        emailSender.sendEmailWithPelicula("",newReserva);
        return new ResponseEntity<>(newReserva, HttpStatus.CREATED);
    }

    @GetMapping("/{IdReserva}")
    @Operation(summary = "trae una reserva por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getReserva(@RequestParam String idReserva){
        Reserva reserva=iReservaService.getReservaById(idReserva);
        validacion.getValidacion(reserva,idReserva,"Reserva NO existe en nuestro sistema de RESERVA.");
        Partido partido=iPartidoService.getPartidoById(reserva.getEvento().getId());
        Pelicula pelicula=iPeliculaService.getPeliculaById(reserva.getEvento().getId());
        if(partido!=null){
            PartidoVO newPartido=new PartidoVO(partido);
            ReservaVOPartidoVO newReserva=new ReservaVOPartidoVO (reserva,newPartido);
            return ResponseEntity.ok(newReserva);
        }else{
            PeliculaVO newPelicula=new PeliculaVO(pelicula);
            ReservaVOwithPeliculaVO newReserva=new ReservaVOwithPeliculaVO (reserva,newPelicula);
            return ResponseEntity.ok(newReserva);
        }
        //ReservaVO response= new ReservaVO(reserva);
        //return null;
    }
}
