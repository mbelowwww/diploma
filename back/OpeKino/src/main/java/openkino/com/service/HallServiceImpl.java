package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.VO.HallVO;
import openkino.com.form.HallForm;
import openkino.com.form.PlaceForm;
import openkino.com.jpa.HallDao;
import openkino.com.jpa.PlaceDao;
import openkino.com.models.Hall;
import openkino.com.models.Place;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallDao hallDao;
    private final PlaceDao placeDao;

    @Override
    public Long save(HallForm hallForm) {

        Hall hall = new HallForm().toHall(hallForm);
        hallDao.save(hall);
        List<Place> places = hallForm.getPlaces().stream()
                .map(placeForm -> new PlaceForm().toPlace(placeForm, hall))
                .collect(Collectors.toList());
        places.forEach(place -> savePlace(place));
        hall.setPlaces(places);
        return hallDao.save(hall).getId();
    }

    @Override
    public List<HallVO> findAll() {
        return hallDao.findAll()
                .stream()
                .map(HallVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Hall findById(Long id) {
        return hallDao.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void deleteHall(Long id) {
        placeDao.findAllByHall_Id(id).forEach(place -> placeDao.delete(place));
        hallDao.deleteById(id);
    }

    private void savePlace(Place place) {
        placeDao.save(place);
    }
}
