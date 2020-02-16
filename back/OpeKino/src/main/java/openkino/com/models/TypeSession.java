package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class TypeSession {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(Views.Public.class)
    @Column
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeSession")
    private List<Session> sessions;

    public TypeSession() {
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

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
