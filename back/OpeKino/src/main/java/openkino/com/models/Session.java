package openkino.com.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Session {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn
    private Film film;

    @ManyToOne
    @JoinColumn
    private Hall hall;

    @ManyToOne
    @JoinColumn
    private TypeSession typeSession;

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public TypeSession getTypeSession() {
        return typeSession;
    }

    public void setTypeSession(TypeSession typeSession) {
        this.typeSession = typeSession;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDateTime getStart() {
        return start;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
