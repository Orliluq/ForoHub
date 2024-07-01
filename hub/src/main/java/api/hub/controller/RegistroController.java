package api.hub.controller;

import api.hub.domain.usuario.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    /***********************************
     * REST API POST
     * Nuevo Registro
     * ENDPOINT :
     * http://localhost:8080/registro
     *************************************/

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            RegistroUsuarioDTO usuario = usuarioService.registrarUsuario(registroUsuarioDTO);
            RespuestaUsuarioDTO respuestaUsuarioDTO;
            respuestaUsuarioDTO = new RespuestaUsuarioDTO(usuario.getId(), usuario.getName());
            URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(respuestaUsuarioDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
        }
    }
}