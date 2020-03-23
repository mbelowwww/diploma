package openkino.com.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table
public class Hall extends AuditEntity {

    @JsonView(Views.Public.class)
    @Column
    private Integer number;

    @Column
    @JsonView(Views.Public.class)
    private Integer width;

    @JsonView(Views.Public.class)
    @Column
    private Integer height;

    @OneToMany(mappedBy = "hall")
    @JsonManagedReference
    private List<Place> places;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
    private List<Session> sessions;

    public Hall() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer heighgt) {
        this.height = heighgt;
    }

    @JsonView(Views.Public.class)
    public List<Place> getPlaces() {
        return places;
    }

    @JsonIgnore
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

}
