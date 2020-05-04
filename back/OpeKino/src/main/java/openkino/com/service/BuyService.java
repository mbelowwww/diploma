package openkino.com.service;


import openkino.com.VO.BuyVO;
import openkino.com.form.BuyForm;
import openkino.com.models.KinoUser;

import java.util.List;

public interface BuyService {
    Long save(BuyForm buyForm);

    BuyVO findBuyById(Long id);

    void deleteBuyById(Long id);

    List<BuyVO> findByKinoUserId(KinoUser kinoUser);
}
