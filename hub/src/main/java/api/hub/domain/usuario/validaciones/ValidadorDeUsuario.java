package api.hub.domain.usuario.validaciones;

import api.hub.domain.topico.RegistroTopicoDTO;
import api.hub.domain.usuario.RegistroUsuarioDTO;
import api.hub.domain.usuario.RespuestaUsuarioDTO;

public interface ValidadorDeUsuario {

    public void validate(RegistroUsuarioDTO RegistroUsuarioDTO);
}