package openkino.com.service;


import openkino.com.models.KinoUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface KinoUserService extends UserDetailsService {
    Long saveKinoUser(KinoUser kinoUser);

    Long updateKinoUser(KinoUser kinoUser);

    KinoUser findKinoUserById(Long id);

    void deleteById(Long id);

    List<KinoUser> findKinoUserAll();

    Long active(KinoUser kinoUser, Boolean active);

    KinoUser findKinoUserByMail(String mail);

    List<KinoUser> findByName(String fname, String name, String lname, String mail);
}


