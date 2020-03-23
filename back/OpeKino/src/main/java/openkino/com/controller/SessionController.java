package openkino.com.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import openkino.com.VO.SessionVO;
import openkino.com.form.SessionForm;
import openkino.com.form.TypeSessionForm;
import openkino.com.models.TypeSession;
import openkino.com.service.SessionService;
import openkino.com.view.Views;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {
    private SessionService sessionService;

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        sessionService.deleteSessionById(id);
    }

    @JsonView(Views.Public.class)
    @PutMapping("/time")
    public List<SessionVO> findAllByTime(@RequestBody SessionForm form) {
        return sessionService.findAllByTime(form.getStart(), form.getEnd());
    }

    @GetMapping("/type/{id}")
    public TypeSession findTypeSessionById(@PathVariable("id") Long id) {
        return sessionService.findTypeSessionById(id);
    }

    @PostMapping("/type")
    public Long saveTypeSession(@RequestBody TypeSessionForm form) {
        return sessionService.saveTypeSession(form);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public SessionVO findById(@PathVariable("id") Long id) {
        return sessionService.findSessionById(id);
    }

    @PostMapping("")
    public Long saveSession(@RequestBody SessionForm sessionForm) {
        return sessionService.saveSession(sessionForm);
    }
}
