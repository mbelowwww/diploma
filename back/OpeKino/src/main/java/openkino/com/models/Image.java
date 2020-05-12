package openkino.com.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import openkino.com.view.Views;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Image extends AuditEntity{

    @Column
    @JsonView(Views.Public.class)
    private String type;

    @Column
    @JsonView(Views.Public.class)
    private Boolean flag;

    @JsonView(Views.Public.class)
    @Column(columnDefinition = "BINARY(5000000)")
    @Lob
    private byte[] image;

    @ManyToOne
    private Film film;
}
