package openkino.com.service;

import openkino.com.form.BanForm;
import openkino.com.models.UserBan;

import java.util.List;

public interface BanService {
    Long save(BanForm banForm);

    Long update(Long id);

    List<UserBan> findAllByIdKinoUser(Long kinoUserId);

    List<UserBan> findAllByStatusAndAndTimeAfter();
}
