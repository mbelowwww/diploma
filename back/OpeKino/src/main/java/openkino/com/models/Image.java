package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;

@Entity
@Table
public class Image extends AuditEntity{

    @Column
    @JsonView(Views.Public.class)
    private String type;

    @Column
    @JsonView(Views.Public.class)
    private Boolean flag;

    @JsonView(Views.Public.class)
    @Column(columnDefinition = "BINARY(5000000)")
    private byte[] image;

    @ManyToOne
    private Film film;


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image_array) {
        this.image = image_array;
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

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
