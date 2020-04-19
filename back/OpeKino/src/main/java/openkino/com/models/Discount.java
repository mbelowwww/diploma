package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table
public class Discount extends AuditEntity{

    @JsonView(Views.Public.class)
    @Column
    private String name;

    @JsonView(Views.Public.class)
    @Column
    private Integer percent;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private List<Buy> buys;

    @Column(name = "DEPRECATED_FROM")
    @JsonView(Views.Public.class)
    private LocalDate deprecatedFrom;

}
