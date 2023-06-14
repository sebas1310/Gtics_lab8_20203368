package com.example.lab8.Controller;

import com.example.lab8.Entity.Evento;
import com.example.lab8.Repository.EventoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {
    final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping(value = "")
    public List<Evento> listarEvento(){
        return eventoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerEventoPorId(
            @PathVariable("id") String idStr) {

        HashMap<String,Object> responseJson = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<Evento> optEvent = eventoRepository.findById(id);
            if (optEvent.isPresent()) {
                responseJson.put("evento",optEvent.get());
                //HTTP- 200 OK
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("msg","Evento NO encontrado");
                responseJson.put("resultado", "falla");
            }
        } catch (NumberFormatException e) {
            responseJson.put("msg","El ID debe ser un numero entero positivo");
            responseJson.put("resultado", "falla");
        }
        return new ResponseEntity<>(responseJson, HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "")
    public ResponseEntity<HashMap<String,Object>> crearArea(@RequestBody Evento evento,@RequestParam(value = "fetchId", required = false) boolean fetchId){
        HashMap<String, Object> responseMap = new HashMap<>();
        if(evento!=null){
                    eventoRepository.save(evento);
                    if(fetchId){
                        responseMap.put("estado:","creado");
                        responseMap.put("id creado", evento.getId());
                    }
                    else {
                        responseMap.put("estado:","creado");
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        }else {
            responseMap.put("msg", "Debe Enviar un evento");
            responseMap.put("estado", "error");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, Object>> gestionExcepcion(
            HttpServletRequest request) {


        HashMap<String, Object> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST")) {
            responseMap.put("msg", "Debe Enviar un evento");
            responseMap.put("estado", "error");
        }
        //HTTP 404 BAD REQUEST
        return ResponseEntity.badRequest().body(responseMap);
    }


}




