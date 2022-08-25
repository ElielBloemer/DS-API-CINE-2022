package com.application.api.controllers;

import com.application.api.model.evento.Seleccion;
import com.application.api.services.interfaces.ISeleccionService;
import com.application.api.vo.SeleccionVO;
import com.application.api.vo.SeleccionVO2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1.0/disenioDeSistemas/seleccion")
@Tag(name = "Seleccion Controller",description =" CONTROLLER CRIADO PARA MANEJAR SELECCIONES")
@CrossOrigin(origins = "*")
@ApiResponses({
        @ApiResponse(responseCode = "400",description = "Bad Request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend error")
})
public class SeleccionController {

    private final ISeleccionService seleccionService;

    @Autowired
    public SeleccionController(ISeleccionService iSeleccionService){
        this.seleccionService = iSeleccionService;
    }

   @PostMapping
   @Operation(summary = "crea uma seleccion")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "criado")
    })
    public ResponseEntity<SeleccionVO> guardarUnaSellecion(@RequestBody SeleccionVO seleccionVO){
        seleccionService.validarInformacion(seleccionVO.nombrePais.toUpperCase());
        Seleccion seleccion = seleccionService.guardarSeleccion(seleccionVO.nombrePais,seleccionVO.continente,null,seleccionVO.mundialesGanados);
        SeleccionVO response = new SeleccionVO(seleccion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

    @GetMapping("/{nombreSeleccion}")
   @Operation(summary = "trae seleccion por nombre")
   @ApiResponses({
           @ApiResponse(responseCode = "200", description = "ok")
   })
   public ResponseEntity<Object>getSeleccion(@RequestParam String nombreSeleccion){
        Seleccion seleccion=seleccionService.getSeleccionPorNombre(nombreSeleccion);
        SeleccionVO2 response= new SeleccionVO2(seleccion);
        return ResponseEntity.ok(response);
   }

    @GetMapping("contieneJugadorEstrella/{nombreSeleccion}")
    @Operation(summary = "averigua si una seleccion tiene al menos algun JUGADOR estrella. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getSeleccionPorJugadorEstrella(@RequestParam String nombreSeleccion){
        boolean contieneJugadorEstrella=seleccionService.contieneJugadorEstrella(nombreSeleccion);
        return ResponseEntity.ok(nombreSeleccion.toUpperCase() + " contiene algun jugador estrella? " + contieneJugadorEstrella);
    }

    @GetMapping("esMuyCampeona/{nombreSeleccion}")
    @Operation(summary = "averigua si una seleccion es muy CAMPEONA. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getSeleccionMuyCampeona(@RequestParam String nombreSeleccion){
        boolean esMuyCampeona=seleccionService.esMuyCampeona(nombreSeleccion);
        return ResponseEntity.ok( nombreSeleccion.toUpperCase() + " es MUY CAMPEONA ? " + esMuyCampeona);
    }

}
