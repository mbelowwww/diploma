package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.exceptions.ResponseException;
import openkino.com.jpa.DiscountDao;
import openkino.com.models.Discount;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    public final DiscountDao discountDao;

    @Override
    public Long saveDiscount(Discount discount) {
        return discountDao.save(discount).getId();
    }

    @Override
    public Discount findDiscountById(Long id) {
        return discountDao.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Скидка с id = " + id +"не найдена!"));
    }

    @Override
    public List<Discount> findAllDiscount() {
        return discountDao.findAll();
    }

    @Override
    public void deleteDiscountById(Long id) {
        Discount discount = findDiscountById(id);
        discount.setDeprecatedFrom(LocalDate.now());
        discountDao.save(discount);
    }
}
