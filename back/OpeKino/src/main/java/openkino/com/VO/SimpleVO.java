package openkino.com.VO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.view.Views;

@Data
public class SimpleVO extends ModelVO {

    @JsonView(Views.Public.class)
    private String name;

    public SimpleVO(Long id, String name) {
        super(id);
        this.name = name;
    }

}
