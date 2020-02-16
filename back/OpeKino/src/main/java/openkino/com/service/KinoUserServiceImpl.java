package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.jpa.KinoUserDao;
import openkino.com.jpa.PositionDao;
import openkino.com.models.KinoUser;
import openkino.com.models.Position;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class KinoUserServiceImpl implements KinoUserService {

    private KinoUserDao kinoUserDao;
    private PositionDao positionDao;

    @Override
    public Long saveKinoUser(KinoUser kinoUser) {
        if (kinoUserDao.findByMale(kinoUser.getMail()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь '" + kinoUser.getMail() + "' уже существует");
        Position position = new Position();
        position.setName("User");
        positionDao.save(position);
        kinoUser.setPosition(position);
        kinoUser.setAction(true);
        kinoUser.setPassword(new BCryptPasswordEncoder().encode(kinoUser.getPassword()));
        return kinoUserDao.save(kinoUser).getId();
    }

    @Override
    public KinoUser findKinoUserById(Long id) {
        return kinoUserDao.findById(id).get();
    }

    @Override
    public Long updateKinoUser(KinoUser kinoUser) {
        KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId()).get();
        if (kinoUser.getLName() != null) kinoUser1.setlName(kinoUser.getlName());
        if (kinoUser.getName() != null) kinoUser1.setName(kinoUser.getName());
        if (kinoUser.getfName() != null) kinoUser1.setfName(kinoUser.getfName());
        if (kinoUser.getAge() != null) kinoUser1.setAge(kinoUser.getAge());
        if (kinoUser.getMail() != null) kinoUser1.setMail(kinoUser.getMail());
        if (kinoUser.getPhone() != null) kinoUser1.setPhone(kinoUser.getPhone());
        if (kinoUser.getPosition() != null) kinoUser1.setPosition(kinoUser.getPosition());
        if (kinoUser.getPassword() != null) kinoUser1.setPassword(kinoUser.getPassword());
        return kinoUserDao.save(kinoUser1).getId();
    }

    @Override
    public void deleteById(Long id) {
        kinoUserDao.deleteById(id);
    }

    @Override
    public List<KinoUser> findKinoUserAll() {
        return kinoUserDao.findAll();
    }

    @Override
    public Long active(KinoUser kinoUser, Boolean active) {
        KinoUser kinoUser1 = kinoUserDao.findById(kinoUser.getId())
                .orElseThrow(NullPointerException::new);
        kinoUser1.setAction(active);
        return kinoUserDao.save(kinoUser1).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KinoUser kinoUser = kinoUserDao.findByMale(username);
        return kinoUser;
    }

    @Override
    public KinoUser findKinoUserByMail(String mail) {
        return kinoUserDao.findByMale(mail);
    }

    @Override
    public List<KinoUser> findByName(String fname, String name, String lname, String mail) {
        if (fname == null & name == null && lname == null && mail == null) return null;
        return kinoUserDao.findByName(fname, name, lname, mail);
    }
}
