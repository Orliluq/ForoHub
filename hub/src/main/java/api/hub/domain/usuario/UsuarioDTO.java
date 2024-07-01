package api.hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(
        @NotBlank(message = "Utilice su correo electrónico como nombre de usuario")
        @Email(message = "Correo electrónico inválido.")
        String email,

       /* @NotBlank(message = "Utilice su correo electrónico como nombre de usuario")
        @Email(message = "Username inválido.")
        String username,*/
        @NotBlank(message = "Debe tener entre 10 y 15 caracteres.")
        String password
) {
}
