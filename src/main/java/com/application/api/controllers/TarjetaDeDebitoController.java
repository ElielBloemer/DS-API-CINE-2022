package com.application.api.controllers;

import com.application.api.model.evento.Actor;
import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import com.application.api.services.interfaces.ITarjetaDeDebitoService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.ActorVO;
import com.application.api.vo.TarjetaDeDebitoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController // essa clase vai receber as requisicoes HTTP
@RequestMapping("/api/V1.0/disenioDeSistemas/tarjeta")
@Tag(name = "TARJETA controller",description = "CONTROLLER CRIADO PARA MANEJAR LAS TARJETAS")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class TarjetaDeDebitoController {

    private final ITarjetaDeDebitoService tarjetaDeDebitoService;
    private final Validacion validacion = new Validacion();

    public TarjetaDeDebitoController(ITarjetaDeDebitoService iTarjetaDeDebito){
        this.tarjetaDeDebitoService=iTarjetaDeDebito;
    }

    @PostMapping
    @Operation(summary = "create a Tarjeta in el system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tarejta created sucessfull")
    })
    public ResponseEntity<TarjetaDeDebitoVO> guardarCard(@RequestBody TarjetaDeDebitoVO tarjetaDeDebitoVO) throws NotFoundException {
        return new ResponseEntity<>(convertorCardToVo(tarjetaDeDebitoService.saveTarjetaDeDebito(tarjetaDeDebitoVO)), HttpStatus.CREATED);
    }

    private TarjetaDeDebitoVO convertorCardToVo(TarjetaDeDebito tarjetaDeDebito){
        return new TarjetaDeDebitoVO(tarjetaDeDebito);
    }

    @GetMapping("/{IdTarjeta}")
    @Operation(summary= "trae una tarjeta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok tarjeta entregue")
    })
    public ResponseEntity<TarjetaDeDebitoVO>getTarjetaVOById(@RequestParam String idTarjeta) throws NotFoundException{
        TarjetaDeDebito tarjetaDeDebito=tarjetaDeDebitoService.getTarjetaDeDebitoByNumber(idTarjeta);
        validacion.getValidacion(tarjetaDeDebito,idTarjeta," NO encontrada en nuestro sistema de Tarjeta");
        return ResponseEntity.ok(convertorCardToVo(tarjetaDeDebito));
    }
}
