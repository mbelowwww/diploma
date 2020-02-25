package openkino.com.service;

import openkino.com.VO.SessionVO;
import openkino.com.form.SessionForm;
import openkino.com.form.TypeSessionForm;
import openkino.com.models.Session;
import openkino.com.models.TypeSession;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionService {

    Long saveTypeSession(TypeSessionForm typeSessionForm);

    Long saveSession(SessionForm sessionForm);

    SessionVO findSessionById(Long id);

    TypeSession findTypeSessionById(Long idTypeSession);

    List<SessionVO> findAllByTime(LocalDateTime start, LocalDateTime end);

    void deleteSessionById(Long id);
}
