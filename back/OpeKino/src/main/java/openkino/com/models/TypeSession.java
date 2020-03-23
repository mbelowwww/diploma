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
public class TypeSession extends AuditEntity{

    @JsonView(Views.Public.class)
    @Column
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeSession")
    private List<Session> sessions;
}
