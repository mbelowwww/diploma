package openkino.com.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.models.Film;
import openkino.com.models.Hall;
import openkino.com.models.Session;
import openkino.com.models.TypeSession;
import openkino.com.view.Views;

import java.time.LocalDateTime;

@Data
public class SessionVO extends ModelVO{

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonView(Views.Public.class)
    private LocalDateTime start;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonView(Views.Public.class)
    private LocalDateTime end;

    @JsonView(Views.Public.class)
    private Film film;

    @JsonView(Views.Public.class)
    private Hall hall;

    @JsonView(Views.Public.class)
    private TypeSession typeSession;

    public SessionVO(Session session){
        super(session.getId());
        this.start = session.getStart();
        this.end = session.getEnd();
        this.film = session.getFilm();
        this.hall = session.getHall();
        this.typeSession = session.getTypeSession();
    }
}
