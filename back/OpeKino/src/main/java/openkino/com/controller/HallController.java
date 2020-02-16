package openkino.com.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import openkino.com.VO.HallVO;
import openkino.com.form.HallForm;
import openkino.com.models.Hall;
import openkino.com.service.HallService;
import openkino.com.view.Views;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall")
@AllArgsConstructor
public class HallController {

    public HallService hallService;

    @PostMapping("")
    public Long save(@RequestBody HallForm hallForm) {
        return hallService.save(hallForm);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/all")
    public List<HallVO> findAll(){
        return hallService.findAll();
    }

    @JsonView(Views.Public.class)
    @GetMapping("")
    public Hall findById(@RequestParam("idHall") Long idHall){
        return hallService.findById(idHall);
    }

    @DeleteMapping
    public void deleteHall(@RequestParam("idHall") Long idHall){
        hallService.deleteHall(idHall);
    }
}
