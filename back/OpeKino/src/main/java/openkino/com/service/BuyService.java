package openkino.com.service;


import openkino.com.form.BuyForm;
import openkino.com.models.Buy;

public interface BuyService {
    Long save(BuyForm buyForm);

    Buy findBuyById(Long id);

    void deleteBuyById(Long id);
}
