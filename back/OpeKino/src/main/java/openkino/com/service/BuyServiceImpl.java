package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.VO.BuyVO;
import openkino.com.form.BuyForm;
import openkino.com.jpa.*;
import openkino.com.models.Buy;
import openkino.com.models.KinoUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class BuyServiceImpl implements BuyService {
    private final BuyDao buyDao;
    private final CardDao cardDao;
    private final ReservationDao reservationDao;
    private final DiscountDao discountDao;

    @Override
    @Transactional
    public BuyVO findBuyById(Long id) {
        Buy buy = buyDao.findById(id).get();
        return new BuyVO(buy);
    }

    @Override
    public Long save(BuyForm buyForm) {
        Buy buy = buyForm.toBuy(cardDao, discountDao, reservationDao);
        return buyDao.save(buy).getId();
    }

    @Override
    public void deleteBuyById(Long id) {
        buyDao.deleteById(id);
    }

    @Override
    public List<BuyVO> findByKinoUserId(KinoUser kinoUser) {
        return buyDao.findAllByKinoUser_Id(kinoUser.getId())
                .stream()
                .map(BuyVO::new)
                .collect(Collectors.toList());
    }
}
