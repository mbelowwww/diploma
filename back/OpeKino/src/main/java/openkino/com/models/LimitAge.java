package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "limit_age")
@Table(name = "LIMIT_AGE")
public class LimitAge extends AuditEntity{

    @JsonView(Views.Public.class)
    @Column(name = "Age")
    private int age;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "limitAge")
    private List<Film> films;
}
