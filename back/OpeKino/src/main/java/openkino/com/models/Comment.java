package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Comment extends AuditEntity{

    @JsonView(Views.Public.class)
    @Column
    private String comment;

    @JsonView(Views.Public.class)
    @Column
    private Rating rating;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.Public.class)
    @Column
    private LocalDateTime dateTime;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private KinoUser kinoUser;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private Film film;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = date;
    }

    public KinoUser getKinoUser() {
        return kinoUser;
    }

    public void setKinoUser(KinoUser kinoUser) {
        this.kinoUser = kinoUser;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public enum Rating {
        one, two, three, four, five, six, seven, eight, nine, ten
    }
}
