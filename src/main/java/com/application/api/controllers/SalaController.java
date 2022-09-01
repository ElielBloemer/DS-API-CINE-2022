package com.application.api.controllers;

import com.application.api.model.evento.*;
import com.application.api.services.interfaces.IPartidoService;
import com.application.api.services.interfaces.IPeliculaService;
import com.application.api.services.interfaces.ISalaService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.ActorVO;
import com.application.api.vo.PartidoVO;
import com.application.api.vo.SalaVO;
import com.application.api.vo.SalaVO2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.awt.*;

@RestController // essa clase vai receber as requisicoes HTTP
@RequestMapping("/api/V1.0/disenioDeSistemas/sala")
@Tag(name = "Sala controller",description = "CONTROLLER created for manager the salas")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class SalaController {

    private final ISalaService iSalaService;
    private final IPeliculaService iPeliculaService;
    private final IPartidoService iPartidoService;

    private final Validacion validacion = new Validacion();

    public SalaController(ISalaService iSalaService,IPeliculaService iPeliculaService,IPartidoService iPartidoService) {
        this.iPartidoService=iPartidoService;
        this.iSalaService = iSalaService;
        this.iPeliculaService=iPeliculaService;
    }

    @PostMapping
    @Operation(summary = "create a sala in System")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "sala created sucessfull")
    })
    public ResponseEntity<SalaVO2> guardarSala(@RequestParam String idSala) throws NotFoundException {
        SalaVO salaVO=new SalaVO(idSala);
        return new ResponseEntity<>(convertorSalaToVo2(iSalaService.saveSala(salaVO)), HttpStatus.CREATED);
    }

    private SalaVO2 convertorSalaToVo2(Sala sala){
        return new SalaVO2(sala);
    }

    @GetMapping("/{IdSala}")
    @Operation(summary= "get a sala for Identificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok actor entregue")
    })
    public ResponseEntity<Object>getSalaVOById(@RequestParam String idSala) throws NotFoundException{
        Sala response=iSalaService.getSalaByIdentificacion(idSala);

        if(response.getEvento()==null){
            return ResponseEntity.ok(convertorSalaToVo2(response));
        }
        Pelicula pelicula=iPeliculaService.getPeliculaById(response.getEvento().getId());
        Partido partido=iPartidoService.getPartidoById(response.getEvento().getId());
        if(pelicula!=null){
            return ResponseEntity.ok(new SalaVO(response.getId(),response.getIdentificacionSala(),response.getAsientosDisponibles(),
                                                                response.getAsientoReservados(),pelicula.getNombrePelicula()));
        } else if (partido!=null) {
            String nombrePartido=partido.getSeleccionA().getNombrePais()+" X "+ partido.getSeleccionB().getNombrePais();
            return ResponseEntity.ok(new SalaVO(response.getId(),response.getIdentificacionSala(),response.getAsientosDisponibles(),
                    response.getAsientoReservados(),nombrePartido.toUpperCase()));
        }
        //return ResponseEntity.ok(convertorSalaToVo(response));
        return null;
    }

    private SalaVO convertorSalaToVo(Sala sala){
        return new SalaVO(sala);
    }

    @PutMapping
    @Operation(summary = "update a sala for sale of the ticket")
    public ResponseEntity<Object>getSalaForSaleTheTicket(@RequestParam String identificacionSala){
        Sala response=iSalaService.uptadeSalaByIdenficacionforSale(identificacionSala);

        if(response.getEvento()==null){
            return ResponseEntity.ok(convertorSalaToVo2(response));
        }
        Pelicula pelicula=iPeliculaService.getPeliculaById(response.getEvento().getId());
        Partido partido=iPartidoService.getPartidoById(response.getEvento().getId());
        if(pelicula!=null){
            return ResponseEntity.ok(new SalaVO(response.getId(),response.getIdentificacionSala(),response.getAsientosDisponibles(),
                    response.getAsientoReservados(),pelicula.getNombrePelicula()));
        } else if (partido!=null) {
            String nombrePartido=partido.getSeleccionA().getNombrePais()+" X "+ partido.getSeleccionB().getNombrePais();
            return ResponseEntity.ok(new SalaVO(response.getId(),response.getIdentificacionSala(),response.getAsientosDisponibles(),
                    response.getAsientoReservados(),nombrePartido.toUpperCase()));
        }

        //return new ResponseEntity<>(new SalaVO(response.getIdentificacionSala(),response.getAsientosDisponibles(),response.getAsientoReservados()),HttpStatus.OK);
        return null;
    }

    @PutMapping("removeEventoInSala/{IdSala}")
    @Operation(summary = "update a sala removing event")
    public ResponseEntity<Object>getSalaForRemoveEvent(@RequestParam String identificacionSala){
        Sala response=iSalaService.getSalaByIdentificacion(identificacionSala.toUpperCase());
        response.setEvento(null);
        response.setTieneEventoAsignado(false);
        Sala newResponse=iSalaService.saveRoomCleaningReturns(response);
        return ResponseEntity.ok(convertorSalaToVo2(newResponse));
       // return null;
    }

    @PutMapping("agendarPelicula/{nombrePelicula}/{IdSala}")
    @Operation(summary = "schuled a Sala for a pelicula")
    public ResponseEntity<SalaVO>getSalaForSchuduleForFilm(@RequestParam String nombrePelicula,String identificacionSala){
        Sala response=iSalaService.saveSalaWithFilm(identificacionSala,nombrePelicula.toUpperCase());
        return new ResponseEntity<>(new SalaVO(response.getId(),response.getIdentificacionSala(),response.getAsientosDisponibles(),response.getAsientoReservados(),nombrePelicula),HttpStatus.OK);
    }

    @PutMapping("schedulerMatch/{IdMatch}/{IdSala}")
    @Operation(summary = "schuled a Sala for a football match")
    public ResponseEntity<SalaVO>getSalaForSchuduleForMatch(@RequestParam Integer idPartido, String identificacionSala){
        Sala response=iSalaService.saveSalaWithMatch(identificacionSala,idPartido);
        Partido partido=iPartidoService.getPartidoById(idPartido);
        validacion.getValidacion(partido,""," THE MATCH IS NOT IN THE SYSTEM");
        String nombrePartido=partido.getSeleccionA().getNombrePais()+" X "+partido.getSeleccionB().getNombrePais();
        return new ResponseEntity<>(new SalaVO(response.getId(),response.getIdentificacionSala(),response.getAsientosDisponibles(),response.getAsientoReservados(),nombrePartido.toUpperCase()),HttpStatus.OK);
    }

    @GetMapping("asientosDisponibles/{IdSala}")
    @Operation(summary= "get a sala for Identifacion and show the available seats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok ")
    })
    public ResponseEntity<Object>getAvailableSeatsOfTheSalaById(@RequestParam String idSala) throws NotFoundException{
        boolean thereIsAvailableSeats= iSalaService.isThereChairAvailable(idSala);
        Sala sala=iSalaService.getSalaByIdentificacion(idSala);
        return ResponseEntity.ok("is there available seats in the Sala : "+ idSala + " ? " + thereIsAvailableSeats +
                ". Available seats: "+sala.getAsientosDisponibles()+
                ". Occupied seats : "+sala.getAsientoReservados() );
    }

}
