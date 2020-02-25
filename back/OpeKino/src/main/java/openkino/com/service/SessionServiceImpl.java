package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.VO.SessionVO;
import openkino.com.exceptions.ResponseException;
import openkino.com.form.SessionForm;
import openkino.com.form.TypeSessionForm;
import openkino.com.jpa.FilmDao;
import openkino.com.jpa.HallDao;
import openkino.com.jpa.SessionDao;
import openkino.com.jpa.TypeSessionDao;
import openkino.com.models.Film;
import openkino.com.models.Session;
import openkino.com.models.TypeSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionDao sessionDao;
    private final FilmDao filmDao;
    private final HallDao hallDao;
    private final TypeSessionDao typeSessionDao;

    @Override
    public Long saveTypeSession(TypeSessionForm form) {
        return typeSessionDao.save(form.toTypeSession()).getId();
    }

    @Override
    public Long saveSession(SessionForm sessionForm) {
        Film film =  filmDao.findById(sessionForm.getIdFilm()).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет фильма с ID = " + sessionForm.getIdFilm()));
        if (sessionDao.findAllByHall_IdAnAndStart(sessionForm.getIdHall(),sessionForm.getStart(),sessionForm.getStart().plusSeconds(film.getLength())).size() > 0)
            throw new ResponseException(HttpStatus.BAD_REQUEST,"В данное время в этом зале уже назначен сеанс!");
        Session session = SessionForm.toSession(
                sessionForm,
                typeSessionDao.findById(sessionForm.getIdTypeSession()).orElseThrow( () -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет типа сеанса с ID = " + sessionForm.getIdTypeSession())),
                hallDao.findById(sessionForm.getIdHall()).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет зала с ID = " + sessionForm.getIdHall())),film);
        return sessionDao.save(session).getId();
    }

    @Override
    public SessionVO findSessionById(Long id) {
        return new SessionVO(sessionDao.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "В базе нет сеанса с ID = " + id)));
    }

    @Override
    public TypeSession findTypeSessionById(Long idTypeSession) {
        return typeSessionDao.findById(idTypeSession).get();
    }

    @Override
    public List<SessionVO> findAllByTime(LocalDateTime start, LocalDateTime end) {
        return sessionDao.findAllByDataTime(start,end).stream()
                .map(SessionVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSessionById(Long id) {
        sessionDao.deleteById(id);
    }
}
