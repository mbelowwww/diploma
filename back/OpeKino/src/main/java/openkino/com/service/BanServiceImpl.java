package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.exceptions.ResponseException;
import openkino.com.form.BanForm;
import openkino.com.jpa.BanDao;
import openkino.com.jpa.KinoUserDao;
import openkino.com.models.KinoUser;
import openkino.com.models.UserBan;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BanServiceImpl implements BanService {
    private final KinoUserDao kinoUserDao;
    private final BanDao banDao;

    @Override
    public Long save(BanForm banForm) {
        KinoUser kinoUserForBan = kinoUserDao.findById(banForm.getKinoUserId()).orElseThrow(() ->
                new ResponseException(HttpStatus.NOT_FOUND, "KinoUser not found by ID = " + banForm.getKinoUserId()));
        UserBan userBan = banForm.toUserBan(kinoUserForBan);
        kinoUserForBan.setUpdatedWhen(LocalDateTime.now());
        kinoUserForBan.setAction(!banForm.getBan());
        userBan.setUpdatedWhen(LocalDateTime.now());
        kinoUserDao.save(kinoUserForBan);
        return banDao.saveAndFlush(userBan).getId();
    }

    @Override
    public Long update(Long id) {
        UserBan userBan = banDao.findById(id).orElseThrow(NullPointerException::new);
        KinoUser kinoUser = kinoUserDao.findById(userBan.getKinoUser().getId()).orElseThrow(NullPointerException::new);
        kinoUser.setAction(true);
        kinoUser.setUpdatedWhen(LocalDateTime.now());
        kinoUserDao.save(kinoUser);
        userBan.setStatus(false);
        userBan.setUpdatedWhen(LocalDateTime.now());
        return banDao.saveAndFlush(userBan).getId();
    }

    @Override
    public List<UserBan> findAllByIdKinoUser(Long kinoUserId) {
        return banDao.findAllByKinoUser_Id(kinoUserId);
    }

    @Override
    public List<UserBan> findAllByStatusAndAndTimeAfter() {
        return banDao.findAllByStatusTrue();
    }

    @Scheduled(fixedRate = 1000*60*5)//каждые 5 минут
    public void removalBan(){ List<UserBan> userBans = banDao.findAllByStatusAndTimeAfter();
        if (userBans.size() > 0){
            userBans.stream().map(userBan -> {
                KinoUser kinoUser = userBan.getKinoUser();
                kinoUser.setAction(true);
                kinoUserDao.save(kinoUser);
                userBan.setUpdatedWhen(LocalDateTime.now());
                userBan.setStatus(false);
                return userBan;

            }).collect(Collectors.toList());
            banDao.saveAll(userBans);
        }
    }
}
