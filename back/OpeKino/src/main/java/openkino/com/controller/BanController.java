package openkino.com.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import openkino.com.form.BanForm;
import openkino.com.models.UserBan;
import openkino.com.service.BanService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ban")
@AllArgsConstructor
public class BanController {
    private final BanService banService;

    @PostMapping("")
    public Long save(@RequestBody BanForm banForm) {
        return banService.save(banForm);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id){
        return banService.update(id);
    }

    @GetMapping("/{id}")
    public List<UserBan> findAllByKinoUserId(@PathVariable Long id){
        return banService.findAllByIdKinoUser(id);
    }

    @GetMapping("/active")
    public List<UserBan> findAllByStatusAndTimeAfter(){
        return banService.findAllByStatusAndAndTimeAfter();
    }
}
