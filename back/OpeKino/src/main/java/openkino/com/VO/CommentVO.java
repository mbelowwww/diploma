package openkino.com.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.models.Comment;
import openkino.com.models.Film;
import openkino.com.models.KinoUser;
import openkino.com.view.Views;

import java.time.LocalDateTime;

@Data
public class CommentVO extends ModelVO{
    @JsonView(Views.Public.class)
    private String comment;
    @JsonView(Views.Public.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateTime;
    @JsonView(Views.Public.class)
    private Long filmId;
    @JsonView(Views.Public.class)
    private KinoUser kinoUser;

    public CommentVO(Comment comment){
        super(comment.getId());
        this.comment = comment.getComment();
        this.dateTime = comment.getDateTime();
        this.filmId = comment.getFilm().getId();
        this.kinoUser = comment.getKinoUser();
    }
}
