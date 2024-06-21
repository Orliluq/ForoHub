package api.hub.controller;

import api.hub.domain.respuesta.*;
import api.hub.domain.topico.TopicoRepository;
import api.hub.domain.usuario.UsuarioRepository;
import api.hub.infra.errors.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/respuesta")
@SecurityRequirement(name="bearer-key")
public class RespuestaController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaService respuestaService;
    @Autowired
    private RespuestaRepository repository;

    /***********************************
     * REST API POST
     * Registrar nueva Respuesta
     * ENDPOINT :
     * http://localhost:8080/respuesta
     *************************************/
    @PostMapping
    @Transactional
    public ResponseEntity respuestaRegistrada (@RequestBody @Valid RespuestaDTO respuestaDTO) throws ValidacionDeIntegridad {
        var respuestaRegistrada = respuestaService.respuestaCreadaDTO(respuestaDTO);
        return ResponseEntity.ok(respuestaRegistrada);
    }

    /**************************************
     * REST API GET
     * Obtener todas las Respuestas
     * ENDPOINT :
     * http://localhost:8080/respuesta/respuestas
     ***************************************/
    @GetMapping("/respuestas")
    public ResponseEntity<Page<ListarRespuestasDTO>>  listarRespuestas(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(repository.findByActiveTrue(paged).map(ListarRespuestasDTO::new));
    }

    /************************************************
     * REST API PUT
     * Actualizar una respuesta por id
     * ENDPOINT :
     * http://localhost:8080/respuesta/1
     *************************************************/
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity respuestaActualizada(@RequestBody @Valid RespuestaActualizadaDTO respuestaActualizadaDTO){
        Respuesta respuesta=repository.getReferenceById(respuestaActualizadaDTO.id());
        respuesta.respuestaActualizada(respuestaActualizadaDTO);
        return ResponseEntity.ok(new RespuestaCreadaDTO(respuesta.getId(),respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate()));
    }

    /************************************************
     * REST API DELETE
     * Eliminar una respuesta por id
     * ENDPOINT :
     * http://localhost:8080/respuesta/1
     *************************************************/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = repository.getReferenceById(id);
        respuesta.diactivateResponse();
        return ResponseEntity.noContent().build();
    }

    /*******************************************
     * REST API GET
     * Obtener una Respuesta pasando el id
     * ENDPOINT :
     * http://localhost:8080/respuesta/1
     ********************************************/
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaCreadaDTO> respuestaCreada(@PathVariable Long id){
        Respuesta respuesta=repository.getReferenceById(id);
        var respuestaRegistrada = new RespuestaCreadaDTO(respuesta.getId(),
                respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate());
        return ResponseEntity.ok(respuestaRegistrada);
    }
}