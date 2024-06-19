package api.hub.domain.topico;

import java.time.LocalDateTime;

public record ListarTopicosDTO(
        Long id,
        String title,
        String message,
        Status status,
        Long usuario_Id,
        String curso,
        LocalDateTime date
) {
    public ListarTopicosDTO (Topico topico){
        this(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getAuthor().getId(),
                topico.getCourse(),
                topico.getDate());

    }
}