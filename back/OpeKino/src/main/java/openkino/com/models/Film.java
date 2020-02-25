package openkino.com.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Film extends AuditEntity{

    @JsonView({Views.Public.class, Views.Internal.class})
    @Column
    private String name;

    @JsonView({Views.Public.class, Views.Internal.class})
    @Column
    private String description;

    @JsonView({Views.Public.class, Views.Internal.class})
    @Column
    private Long length;


    @JsonView({Views.Public.class, Views.Internal.class})
    @Column
    private Float rating;

    @JsonView({Views.Public.class, Views.Internal.class})
    @ManyToOne
    @JoinColumn(name = "ID_LIMIT_AGE")
    private LimitAge limitAge;


    @JsonView({Views.Public.class, Views.Internal.class})
    @ManyToOne
    @JoinColumn(name = "ID_GENRE")
    private Genre genre;

    @JsonView({Views.Public.class, Views.Internal.class})
    @OneToMany(mappedBy = "film")
    private List<Image> image;

    @OneToMany(mappedBy = "film")
    private List<Session> sessions;

    @OneToMany(mappedBy = "film")
    private List<Comment> comments;

    public Film() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    @JsonIgnore
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long lenght) {
        this.length = lenght;
    }

    public LimitAge getLimitAge() {
        return limitAge;
    }

    public void setLimitAge(LimitAge limitAge) {
        this.limitAge = limitAge;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @JsonIgnore
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
