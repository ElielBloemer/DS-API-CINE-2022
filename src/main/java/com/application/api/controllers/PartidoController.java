package com.application.api.controllers;

import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Seleccion;
import com.application.api.services.interfaces.IPartidoService;
import com.application.api.services.interfaces.ISeleccionService;
import com.application.api.vo.PartidoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/api/V1.0/disenioDeSistemas/match")
@Tag(name="Match football controller",description = "controller created for manager the matchs")
@CrossOrigin(origins = "*")
@ApiResponses({
        @ApiResponse(responseCode = "400",description = "Bad Request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend error")
})
public class PartidoController {

    private final IPartidoService iPartidoService;
    private final ISeleccionService iSeleccionService;

    @Autowired
    public PartidoController(IPartidoService iPartidoService, ISeleccionService iSeleccionService) {
        this.iPartidoService = iPartidoService;
        this.iSeleccionService = iSeleccionService;
    }

    @GetMapping("/{IdMatch}")
    @Operation(summary = "Get every matches")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object> getPartidoByid(@RequestParam Integer idPartido){
        Partido partidoById=iPartidoService.getPartidoById(idPartido);
        PartidoVO response = new PartidoVO(partidoById);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "create a football match in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "actor created sucessfull")
    })
    public ResponseEntity<PartidoVO>guardarActor(@RequestParam String nameTeamA,String nameTeamB) throws NotFoundException {
        return new ResponseEntity<>(convertorMatchToVo(iPartidoService.setPartidoWithSelecciones(nameTeamA,nameTeamB)), HttpStatus.CREATED);
    }

    private PartidoVO convertorMatchToVo(Partido partido){
        return new PartidoVO(partido);
    }

    @GetMapping("isInteresting/{IdMatch}")
    @Operation(summary = "check out if a films is interesting")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getMatchIfIsIsteresting(@RequestParam Integer idPartido){
        Partido partido=iPartidoService.getPartidoById(idPartido);
        boolean esInteresante=iPartidoService.interestingCriteria(idPartido);
        return ResponseEntity.ok(" The Football Match between "+ partido.getSeleccionA().getNombrePais().toUpperCase() + " X " +
                partido.getSeleccionB().getNombrePais().toUpperCase() + " is interesting? " + (esInteresante));
    }

}
