package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.VO.BuyVO;
import openkino.com.form.BuyForm;
import openkino.com.models.KinoUser;
import openkino.com.service.BuyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buy")
@AllArgsConstructor
public class BuyController {
    private final BuyService buyService;

    @PostMapping("")
    public Long save(@RequestBody BuyForm buyForm){
        return buyService.save(buyForm);
    }

    @GetMapping("/{id}")
    public BuyVO findById(@PathVariable Long id){
        return buyService.findBuyById(id);
    }

    @GetMapping("/user")
    public List<BuyVO> findByKInoUser(@AuthenticationPrincipal KinoUser kinoUser){
        return buyService.findByKinoUserId(kinoUser);
    }
}
