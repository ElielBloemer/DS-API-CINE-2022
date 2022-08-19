package com.application.api.controllers;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Jugador;
import com.application.api.services.interfaces.IJugadorService;
import com.application.api.vo.ActorVO;
import com.application.api.vo.JugadorVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.xml.bind.ValidationException;

@RestController // essa clase vai receber as requisicoes HTTP
@RequestMapping("/api/V1.0/disenioDeSistemas/jugador")
@Tag(name = "Jugador controller",description = "Controller criado para gestionar los jugadores usados")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class JugadorController {

    private final IJugadorService jugadorService;

    @Autowired
    public JugadorController(IJugadorService jugadorService){
        this.jugadorService=jugadorService;
    }

    @GetMapping("/{id}")
    @Operation(summary= "trae jugador por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok jugador entregue")
    })
    public ResponseEntity<JugadorVO> getJugadorVOById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(convertorJugadorToVO(jugadorService.getJugadorById(id)));
    }

    @PostMapping
    @Operation(summary = "Crio jugador na base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "jugador criado con exito")
    })
    public ResponseEntity<JugadorVO>guardarJugador(@RequestBody JugadorVO jugadorVO) throws ValidationException {
        return new ResponseEntity<>(convertorJugadorToVO(jugadorService.guardarJugador(jugadorVO)), HttpStatus.CREATED);
    }

    private JugadorVO convertorJugadorToVO(Jugador jugador){
        return new JugadorVO(jugador);
    }
}
