package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.exceptions.ResponseException;
import openkino.com.form.SessionForm;
import openkino.com.jpa.FilmDao;
import openkino.com.jpa.HallDao;
import openkino.com.jpa.SessionDao;
import openkino.com.jpa.TypeSessionDao;
import openkino.com.models.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionDao sessionDao;
    private final FilmDao filmDao;
    private final HallDao hallDao;
    private final TypeSessionDao typeSessionDao;

    @Override
    public Long saveSession(SessionForm sessionForm) {
        Session session = SessionForm.toSession(
                sessionForm,
                typeSessionDao.findById(sessionForm.getIdTypeSession()).orElseThrow( () -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет типа сеанса с ID = " + sessionForm.getIdTypeSession())),
                hallDao.findById(sessionForm.getIdHall()).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет залас ID = " + sessionForm.getIdHall())),
                filmDao.findById(sessionForm.getIdFilm()).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет фильма с ID = " + sessionForm.getIdFilm())));
        return sessionDao.save(session).getId();
    }
}
