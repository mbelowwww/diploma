package openkino.com.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import openkino.com.models.Film;
import openkino.com.models.Hall;
import openkino.com.models.Session;
import openkino.com.models.TypeSession;
import openkino.com.service.Helper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SessionForm {

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime start;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime end;

    private Long idFilm;
    private Long idHall;
    private Long idTypeSession;
    private BigDecimal price;

    public static Session toSession(SessionForm form, TypeSession typeSession, Hall hall, Film film) {
        Session session = new Session();
        session.setStart(form.getStart());
        session.setEnd(session.getStart().plusSeconds(film.getLength()));
        session.setTypeSession(typeSession);
        session.setHall(hall);
        session.setFilm(film);
        session.setPrice(form.getPrice());
        Helper.auditOnCreate(session);
        return session;
    }
}
