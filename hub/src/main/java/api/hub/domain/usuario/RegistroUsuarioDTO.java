package api.hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistroUsuarioDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank (message = "Utilice su correo electrónico como nombre de usuario")
        String username,
        @NotBlank(message = "Debe tener entre 10 y 15 caracteres.") @Pattern(regexp = "\\d{10,15}")
        String password
) {
}