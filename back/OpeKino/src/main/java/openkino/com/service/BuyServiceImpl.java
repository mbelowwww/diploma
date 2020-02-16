package openkino.com.service;

import openkino.com.jpa.BuyDao;
import openkino.com.jpa.CardDao;
import openkino.com.jpa.KinoUserDao;
import openkino.com.jpa.ReservationDao;
import openkino.com.models.Buy;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BuyServiceImpl implements BuyService {
    final static Logger logger = Logger.getLogger(BuyServiceImpl.class);
    private BuyDao buyDao;
    private CardDao cardDao;
    private KinoUserDao kinoUserDao;
    private ReservationDao reservationDao;

    public BuyServiceImpl(BuyDao buyDao, CardDao cardDao, KinoUserDao kinoUserDao, ReservationDao reservationDao) {
        this.buyDao = buyDao;
        this.cardDao = cardDao;
        this.kinoUserDao = kinoUserDao;
        this.reservationDao = reservationDao;
    }

    @Override
    public Buy findBuyById(Long id) {
        return buyDao.findById(id).get();
    }

//    public Long saveBuy(BuyMask buyMask, KinoUser kinoUser) {
//        Buy buy = new Buy();
//        buy.setReservation(reservationDao.findById(buyMask.getIdReservation()).get());
//        Reservation reservation = reservationDao.findById(buy.getReservation().getId()).get();
//        buy.setPrice(reservation.getPrice());
//        reservation.setBuy(buy);
//
//
//        if (cardDao.findById(buyMask.getCardMask().getId())!= null) {//Если каты нет в базе
//            Card card = new Card();
//            card.setId(buyMask.getCardMask().getId());
//            card.setBalance(buyMask.getCardMask().getBalance());
//            buy.setCard(card);
//            card = buy.getCard();
//            BigDecimal result = new BigDecimal(String.valueOf(
//                    buy.getCard().getBalance().subtract(buy.getPrice())));//результат вычисления
//            if (kinoUser != null) {//Если пользователь авторизовался
//                card.setKinoUser(kinoUser);
//                KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId()).get();
//
//                if (result.compareTo(BigDecimal.valueOf(0)) >= 0) {
//                    card.setBalance(result);
//                    card.setKinoUser(kinoUser1);
//                    return buyDao.save(buy).getId();
//                } else throw new HandlerException("not enough money");
//            } else {
//                throw new HandlerException("no user");
//            }
//        }
//        else {
//            Card card =cardDao.findById(buyMask.getCardMask().getId()).get();
//            buy.setCard(card);
//            BigDecimal result = new BigDecimal(String.valueOf(
//                    buy.getCard().getBalance().subtract(buy.getPrice())));//результат вычисления
//            if (result.compareTo(BigDecimal.valueOf(0)) >= 0) {
//                card.setBalance(result);
//                return buyDao.save(buy).getId();
//            } else throw new HandlerException("not enough money");
//        }
//    }


    @Override
    public void deleteBuyById(Long id) {
        buyDao.deleteById(id);
    }
}
