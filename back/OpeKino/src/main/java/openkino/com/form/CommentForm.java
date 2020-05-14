package openkino.com.form;

import lombok.Data;
import openkino.com.exceptions.ResponseException;
import openkino.com.jpa.FilmDao;
import openkino.com.models.Comment;
import openkino.com.models.KinoUser;
import openkino.com.service.Helper;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CommentForm {
    private Long id;
    private String comment;
    private Long filmId;

    public Comment toComment(KinoUser kinoUser, FilmDao filmDao) {
        Comment comment = new Comment();
        comment.setKinoUser(kinoUser);
        update(comment, filmDao);
        Helper.auditOnCreate(comment);
        return comment;
    }

    public Comment update(Comment comment, FilmDao filmDao) {
        comment.setComment(this.comment);
        comment.setDateTime(LocalDateTime.now());
        if (filmId != null)
            comment.setFilm(filmDao.findById(filmId)
                    .orElseThrow(()-> new ResponseException(HttpStatus.BAD_REQUEST, "Фильм с id = "+ filmId + " не найден!")));
        Helper.auditOnUpdate(comment);
        return comment;
    }
}
