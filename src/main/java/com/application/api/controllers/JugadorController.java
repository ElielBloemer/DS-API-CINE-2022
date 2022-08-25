package com.application.api.controllers;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Jugador;
import com.application.api.services.interfaces.IJugadorService;
import com.application.api.vo.ActorVO;
import com.application.api.vo.JugadorVO;
import com.application.api.vo.SeleccionVO;
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
@Tag(name = "Jugador controller",description = "CONTROLLER CRIADO PARA MANEJAR JUGADORES")
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

    @GetMapping("/{nombre}")
    @Operation(summary= "trae jugador por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok jugador entregue")
    })
    public ResponseEntity<Object> getJugador(@RequestParam String nombreJugador) {
        Jugador jugador = jugadorService.getJugadorByNombre(nombreJugador);
        JugadorVO response= new JugadorVO(jugador);
        return ResponseEntity.ok(response);
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
