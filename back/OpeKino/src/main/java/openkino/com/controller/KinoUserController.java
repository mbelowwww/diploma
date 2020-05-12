package openkino.com.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import openkino.com.models.KinoUser;
import openkino.com.service.KinoUserService;
import openkino.com.view.Views;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kinouser")
@AllArgsConstructor
public class KinoUserController {

    private KinoUserService kinoUserService;

    @JsonView(Views.Public.class)
    @GetMapping("/find")
    public List<KinoUser> findByName(
            @RequestParam(value = "fname",required = false) String fname,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "lname",required = false) String lname,
            @RequestParam(value = "mail",required = false) String mail
                               ){
        return kinoUserService.findByName(fname, name, lname,mail);
    }

    @JsonView(Views.Public.class)
    @PostMapping("")
    public Long saveKinoUser(@RequestBody KinoUser kinoUser) {
        return kinoUserService.saveKinoUser(kinoUser);
    }

    @JsonView(Views.Public.class)
    @PutMapping("")
    public Long updateKinoUser(@RequestBody KinoUser kinoUser) {
        return kinoUserService.updateKinoUser(kinoUser);
    }

    @JsonView(Views.Public.class)
    @DeleteMapping("/delete/{id}")
    public void deleteKinoUser(@PathVariable Long id) {
        kinoUserService.deleteById(id);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/all")
    public List<KinoUser> getAll() {
        return kinoUserService.findKinoUserAll();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public KinoUser get(@PathVariable Long id) {
        return kinoUserService.findKinoUserById(id);
    }

    @JsonView(Views.Public.class)
    @GetMapping("")
    public KinoUser findByAuth(@AuthenticationPrincipal KinoUser kinoUser){
        return kinoUserService.findByAuth(kinoUser);
    }
}
