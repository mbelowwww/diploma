package openkino.com.VO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import openkino.com.view.Views;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelVO {
    @JsonView(Views.Public.class)
    private Long id;
}
