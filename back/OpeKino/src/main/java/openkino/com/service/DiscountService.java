package openkino.com.service;

import openkino.com.models.Discount;

import java.util.List;

public interface DiscountService {
    Long saveDiscount(Discount discount);
    Discount findDiscountById(Long id);
    List<Discount> findAllDiscount();
    void deleteDiscountById(Long id);
}
