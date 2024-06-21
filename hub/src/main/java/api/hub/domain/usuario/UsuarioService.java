package api.hub.domain.usuario;

import api.hub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registrarUsuario(RegistroUsuarioDTO registroUsuarioDTO) {
        // Verificar si el correo electrónico ya está registrado en la base de datos
        if (usuarioRepository.existsByEmail(registroUsuarioDTO.email())) {
            throw new ValidacionDeIntegridad("Este correo electrónico ya está registrado.");
        }

        // Verificar si el nombre de usuario ya está en uso
        if (usuarioRepository.existsByUsername(registroUsuarioDTO.username())) {
            throw new ValidacionDeIntegridad("Este nombre de usuario ya está en uso.");
        }

        // Crear un nuevo usuario y cifrar la contraseña
        var usuario = new Usuario(registroUsuarioDTO, passwordEncoder);
        usuarioRepository.save(usuario);
    }

    public void actualizarUsuario(Long id, ActualizacionUsuarioDTO actualizacionUsuarioDTO) {
        // Verificar si el usuario existe en la base de datos
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("El usuario con el ID proporcionado no existe.");
        }

        var usuario = usuarioOptional.get();

        // Actualizar los campos del usuario si se proporcionan nuevos valores
        usuario.actualizacionUsuario(actualizacionUsuarioDTO);

        // Guardar el usuario actualizado en la base de datos
        usuarioRepository.save(usuario);
    }

    public void desactivarUsuario(Long id) {
        // Verificar si el usuario existe en la base de datos
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("El usuario con el ID proporcionado no existe.");
        }

        var usuario = usuarioOptional.get();

        // Desactivar el usuario
        usuario.deactivateUser();

        // Guardar el usuario desactivado en la base de datos
        usuarioRepository.save(usuario);
    }
}
