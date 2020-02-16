package openkino.com.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "POSITION")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private List<KinoUser> kinoUsers;

    public Position(){}

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

    public void setKinoUsers(List<KinoUser> kinoUsers) {
        this.kinoUsers = kinoUsers;
    }

    public List<KinoUser> getKinoUsers() {
        return kinoUsers;
    }
}
