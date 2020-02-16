package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.form.SessionForm;
import openkino.com.service.SessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {
    private SessionService sessionService;

    @PostMapping("")
    public Long saveSession(@RequestBody SessionForm sessionForm){
        return sessionService.saveSession(sessionForm);
    }
}
