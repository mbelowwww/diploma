package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class UserBan extends AuditEntity {

    @JsonView(Views.Public.class)
    @Column
    private Boolean status;

    @JsonView(Views.Public.class)
    @Column
    private String description;

    @JsonView(Views.Public.class)
    @Column
    private LocalDateTime time;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn
    private KinoUser kinoUser;

}
