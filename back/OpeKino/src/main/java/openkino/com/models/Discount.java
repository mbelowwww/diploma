package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.view.Views;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Discount {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(Views.Public.class)
    @Column
    private String name;

    @JsonView(Views.Public.class)
    @Column
    private Integer percent;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private List<Buy> buys;

    public Discount() {
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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public List<Buy> getBuys() {
        return buys;
    }

    public void setBuys(List<Buy> buys) {
        this.buys = buys;
    }
}
