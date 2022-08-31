package com.application.api.controllers;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Sala;
import com.application.api.services.interfaces.ISalaService;
import com.application.api.vo.ActorVO;
import com.application.api.vo.SalaVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

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

    public SalaController(ISalaService iSalaService) {
        this.iSalaService = iSalaService;
    }


    @PostMapping
    @Operation(summary = "create sala in System")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "sala created sucessfull")
    })
    public ResponseEntity<SalaVO> guardarActor(@RequestBody SalaVO salaVO) throws NotFoundException {
        return new ResponseEntity<>(convertorSalaToVo(iSalaService.saveSala(salaVO)), HttpStatus.CREATED);
    }

    private SalaVO convertorSalaToVo(Sala sala){
        return new SalaVO(sala);
    }

    @GetMapping("/{IdSala}")
    @Operation(summary= "get a sala for Identifacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok actor entregue")
    })
    public ResponseEntity<SalaVO>getSalaVOById(@RequestParam String idSala) throws NotFoundException{
        return ResponseEntity.ok(convertorSalaToVo(iSalaService.getSalaByIdentificacion(idSala)));
    }

    @PutMapping
    @Operation(summary = "update a sala for sale of the ticket")
    public ResponseEntity<SalaVO>getSalaForSaleTheTicket(@RequestParam String identificacionSala){
        Sala response=iSalaService.uptadeSalaByIdenficacionforSale(identificacionSala);
        return new ResponseEntity<>(new SalaVO(response),HttpStatus.OK);
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
