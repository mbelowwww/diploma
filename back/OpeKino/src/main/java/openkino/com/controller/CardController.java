package openkino.com.controller;

import lombok.AllArgsConstructor;
import openkino.com.exceptions.ResponseException;
import openkino.com.form.CardForm;
import openkino.com.models.Card;
import openkino.com.models.KinoUser;
import openkino.com.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("")
    public Long saveCard(@RequestBody CardForm cardForm, @AuthenticationPrincipal KinoUser kinoUser) {
        return cardService.saveCard(cardForm, kinoUser);
    }

    @PutMapping("")
    public Long addBalance(@RequestParam("numberCard") Long cardId, @RequestParam("money") BigDecimal money) {
        if (money.longValue() < 0)
            throw new ResponseException(HttpStatus.BAD_REQUEST,"Невозможно добавить отрицательную сумму!!!!!");
        return cardService.addBalance(cardId,money);
    }

    @GetMapping("/{id}")
    public Card findUserFilmById(@PathVariable Long id) {
        return cardService.findCardById(id);
    }

    @GetMapping("/user")
    public List<Card> findUserFilmById(@AuthenticationPrincipal KinoUser kinoUser) {
        return cardService.findAllCardsByIdFilmUser(kinoUser);
    }

}
