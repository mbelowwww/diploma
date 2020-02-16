package openkino.com.form;

import lombok.Data;
import openkino.com.models.Hall;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HallForm {
    private Integer width;
    private Integer height;
    private List<PlaceForm> places;
    private Integer number;
    private BigDecimal price;

    public Hall toHall(HallForm form){
        Hall hall = new Hall();
        hall.setHeight(form.getHeight());
        hall.setWidth(form.getWidth());
        hall.setNumber(form.getNumber());
        hall.setPrice(form.getPrice());
        return hall;
    }
}
