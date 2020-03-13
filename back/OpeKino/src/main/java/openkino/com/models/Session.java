package openkino.com.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
public class Session extends AuditEntity {

    @JsonView(Views.Public.class)
    @Column
    private BigDecimal price;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonView(Views.Public.class)
    @Column
    private LocalDateTime start;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonView(Views.Public.class)
    @Column
    private LocalDateTime end;

    @JsonView(Views.Public.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn
    private Film film;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private Hall hall;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private TypeSession typeSession;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    private List<Reservation> reservations;
}
