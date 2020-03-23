package openkino.com.VO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.models.Hall;
import openkino.com.view.Views;

import java.math.BigDecimal;

@Data
public class HallVO extends ModelVO {
    @JsonView(Views.Public.class)
    private Integer width;

    @JsonView(Views.Public.class)
    private Integer height;

    @JsonView(Views.Public.class)
    private Integer number;


    @JsonView(Views.Public.class)
    private Integer quantityPlaces;

    public HallVO(Hall hall) {
        super(hall.getId());
        this.height = hall.getHeight();
        this.width = hall.getWidth();
        this.number = hall.getNumber();
        this.quantityPlaces = hall.getPlaces().size();
    }
}
