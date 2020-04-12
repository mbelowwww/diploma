package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.models.Discount;
import openkino.com.service.DiscountService;
import openkino.com.service.Helper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("discount")
@AllArgsConstructor
public class DiscountController {
    public final DiscountService discountService;

    @PostMapping("")
    public Long save(@RequestBody Discount discount) {
        Helper.auditOnCreate(discount);
        return discountService.saveDiscount(discount);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        discountService.deleteDiscountById(id);
    }

    @GetMapping("/all")
    public List<Discount> findAll(){
        return discountService.findAllDiscount();
    }

    @GetMapping("{id}")
    public Discount findById(@PathVariable("id") Long id){
        return discountService.findDiscountById(id);
    }
}
