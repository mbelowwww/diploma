package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.form.BuyForm;
import openkino.com.jpa.*;
import openkino.com.models.Buy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class BuyServiceImpl implements BuyService {
    private final BuyDao buyDao;
    private final CardDao cardDao;
    private final ReservationDao reservationDao;
    private final DiscountDao discountDao;

    @Override
    public Buy findBuyById(Long id) {
        return buyDao.findById(id).get();
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
}
