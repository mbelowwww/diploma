package openkino.com.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import openkino.com.models.Hall;
import openkino.com.models.Place;

@Data
@NoArgsConstructor
public class PlaceForm {
    private Integer y;
    private Integer x;

    public Place toPlace(PlaceForm placeForm, Hall hall) {
        Place place = new Place();
        place.setX(placeForm.getX());
        place.setY(placeForm.getY());
        place.setHall(hall);
        return place;
    }
}
