package openkino.com.service;

import openkino.com.form.SessionForm;
import openkino.com.models.Session;

public interface SessionService {

    Long saveSession(SessionForm sessionForm);

}
