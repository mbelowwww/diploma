package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.form.BuyForm;
import openkino.com.service.BuyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
@AllArgsConstructor
public class BuyController {
    private final BuyService buyService;

    @PostMapping("")
    public Long save(@RequestBody BuyForm buyForm){
        return buyService.save(buyForm);
    }
}
