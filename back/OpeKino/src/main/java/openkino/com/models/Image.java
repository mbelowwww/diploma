package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;

@Entity
@Table
public class Image {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    @JsonView(Views.Public.class)
    private String type;

    @JsonView(Views.Public.class)
    @Column(columnDefinition = "BINARY(5000000)")
    private byte[] image_array;

    @OneToOne
    private Film film;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage_array() {
        return image_array;
    }

    public void setImage_array(byte[] image_array) {
        this.image_array = image_array;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
