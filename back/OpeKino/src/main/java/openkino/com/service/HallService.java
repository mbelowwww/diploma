package openkino.com.service;

import openkino.com.VO.HallVO;
import openkino.com.form.HallForm;
import openkino.com.models.Hall;

import java.util.List;

public interface HallService {

    Long save(HallForm hallForm);

    List<HallVO> findAll();

    Hall findById(Long id);

    void deleteHall(Long id);
}
