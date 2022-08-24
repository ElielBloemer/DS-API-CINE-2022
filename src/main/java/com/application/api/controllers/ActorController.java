package com.application.api.controllers;


import com.application.api.vo.ActorVO;
import com.application.api.model.evento.Actor;
import com.application.api.services.interfaces.IActorService;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController // essa clase vai receber as requisicoes HTTP
@RequestMapping("/api/V1.0/disenioDeSistemas/actor")
@Tag(name = "Actor controller",description = "Controller criado para gestionar los actores usados")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class ActorController {
    private final IActorService actorService;

    @Autowired
    public ActorController(IActorService actorService){

        this.actorService=actorService;
    }

    @GetMapping
    @Operation(summary = "Trae a todos los actores de la BD")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "ok todo los actores")
    })
    public ResponseEntity<List<ActorVO>> getJugadoresVO(){
        return ResponseEntity.ok(converterActoresToVo(actorService.getTodoLosActores()));
    }

    @GetMapping("/{id}")
    @Operation(summary= "trae actores por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " ok actor entregue")
    })
    public ResponseEntity<ActorVO>getActorVOById(@PathVariable Integer id) throws NotFoundException{
        return ResponseEntity.ok(convertorActorToVo(actorService.getActorById(id)));
    }

    @PostMapping
    @Operation(summary = "Crio actores na base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "actor criado con exito")
    })
    public ResponseEntity<ActorVO>guardarActor(@RequestBody ActorVO actorVO) throws ValidationException{
        return new ResponseEntity<>(convertorActorToVo(actorService.guardarActor(actorVO)), HttpStatus.CREATED);
    }

    private  List<ActorVO>converterActoresToVo(List<Actor> actores){
        return actores.stream().map(this::convertorActorToVo).collect(Collectors.toList());
    }
    private ActorVO convertorActorToVo(Actor actor){
        return new ActorVO(actor);
    }

}
