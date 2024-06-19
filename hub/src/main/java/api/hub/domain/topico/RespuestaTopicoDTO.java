package api.hub.domain.topico;

import java.time.LocalDateTime;

public record RespuestaTopicoDTO(
        Long id,
        String title,
        String message,
        Status status,
        Long usuario_Id,
        String curso,
                                LocalDateTime date) {
    public RespuestaTopicoDTO(Topico topicoId) {
        this(
                topicoId.getId(),
                topicoId.getTitle(),
                topicoId.getMessage(),
                topicoId.getStatus(),
                topicoId.getAuthor().getId(),
                topicoId.getCourse(),
                topicoId.getDate());
    }
}