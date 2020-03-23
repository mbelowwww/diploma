package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.form.CardForm;
import openkino.com.models.Card;
import openkino.com.models.KinoUser;
import openkino.com.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("")
    public Long saveCard(@RequestBody CardForm cardForm){
        return cardService.saveCard(cardForm,null);
    }

    @GetMapping("/{id}")
    public Card findUserFilmById(@PathVariable Long id){
        return cardService.findCardById(id);
    }

    @GetMapping("/user")
    public List<Card> findUserFilmById(KinoUser kinoUser){
        return cardService.findAllCardsByIdFilmUser(kinoUser);
    }

}
