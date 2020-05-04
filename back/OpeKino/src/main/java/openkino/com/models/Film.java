package openkino.com.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
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

    @JsonIgnore
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @JsonIgnore
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

}
