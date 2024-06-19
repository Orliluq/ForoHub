package api.hub.controller;

import api.hub.domain.usuario.RegistroUsuarioDTO;
import api.hub.domain.usuario.Usuario;
import api.hub.domain.usuario.UsuarioRepository;
import api.hub.domain.usuario.RespuestaUsuarioDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    /***********************************
     * REST API POST
     * Nuevo Registro
     * ENDPOINT :
     * http://localhost:8080/registro
     *************************************/
    @PostMapping
    @Transactional
    public ResponseEntity registroUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO, UriComponentsBuilder uriComponentsBuilder){
        try {
            Usuario usuario = usuarioRepository.save(new Usuario(registroUsuarioDTO,bCryptPasswordEncoder));
            RespuestaUsuarioDTO respuestaUsuarioDTO = new RespuestaUsuarioDTO(
                    usuario.getId(), usuario.getName()
            );
            URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(respuestaUsuarioDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
        }
    }

}