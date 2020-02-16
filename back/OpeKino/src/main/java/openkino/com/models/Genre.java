package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "GENRE")
public class Genre {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @JsonView(Views.Public.class)
    @Column(name = "Name")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private List<Film> films;

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
