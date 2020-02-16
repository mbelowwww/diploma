package openkino.com.service;


import openkino.com.models.Buy;

public interface BuyService {
    //    Long saveBuy(BuyMask buyMask, KinoUser kinoUser) ;//покупка c регистрацией
    Buy findBuyById(Long id);

    void deleteBuyById(Long id);
}
