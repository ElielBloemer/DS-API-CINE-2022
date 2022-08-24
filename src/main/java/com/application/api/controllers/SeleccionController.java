package com.application.api.controllers;

import com.application.api.model.evento.Seleccion;
import com.application.api.services.interfaces.ISeleccionService;
import com.application.api.vo.SeleccionVO;
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
@Tag(name = "Seleccion Controller",description =" Controller criado para gestionar selecciones")
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
   @Operation(summary = "cria uma seleccion")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "criado")
    })
    public ResponseEntity<SeleccionVO> guardarUnaSellecion(@RequestBody SeleccionVO seleccionVO){
        Seleccion seleccion = seleccionService.guardarSeleccion(seleccionVO.nombrePais,seleccionVO.continente,seleccionVO.jugadorTitulares,seleccionVO.mundialesGanados);
        SeleccionVO response = new SeleccionVO(seleccion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

   @GetMapping
   @Operation
   @ApiResponses({
           @ApiResponse(responseCode = "200", description = "ok")
   })
   public ResponseEntity<Object>getSeleccion(@RequestParam String nombre){
        Seleccion seleccion=seleccionService.getSeleccionPorNombre(nombre);
        SeleccionVO response= new SeleccionVO(seleccion);
        return ResponseEntity.ok(response);
   }
}
