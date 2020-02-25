package openkino.com.models;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;
import javax.persistence.*;

@Data
@MappedSuperclass
public class ModelEntity {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue
    @Column
    private Long id;

}
