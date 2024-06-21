package api.hub.domain.topico;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TopicoActualizadoDTO(
        @NotNull Long id,
        String title,
        String message,
        Status status,
        @NotNull Long usuario_Id,
        String curso,
        LocalDateTime date
) {
}
