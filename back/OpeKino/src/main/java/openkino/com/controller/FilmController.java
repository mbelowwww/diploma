package openkino.com.controller;

import com.fasterxml.jackson.annotation.JsonView;
import openkino.com.form.ImageSaveForm;
import openkino.com.models.Film;
import openkino.com.models.Genre;
import openkino.com.models.LimitAge;
import openkino.com.service.FilmService;
import openkino.com.view.Views;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("")
    public Long saveFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @PutMapping("")
    public Long updateFilm(@RequestBody Film film) {
        return filmService.updateFilmById(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable Long id) {
        filmService.deleteFilmById(id);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{id}")
    public Film get(@PathVariable Long id) {
        return filmService.findFilmById(id);
    }

    @JsonView(Views.Internal.class)
    @GetMapping("")
    public List<Film> getAll(@RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "limitAge", required = false) Integer limitAge,
                             @RequestParam(name = "rating", required = false) Double rating,
                             @RequestParam(name = "genre", required = false) String genre) {
        return filmService.findFilmsByVars(name, genre, rating, limitAge);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/all")
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/genre")
    @JsonView(Views.Internal.class)
    public List<Genre> getGenre() {
        return filmService.findAllGenre();
    }

    @PutMapping("/genre")
    public Long updateGenre(@RequestBody Genre genre) {
        return filmService.updateGenre(genre);
    }

    @GetMapping("/limitAge")
    @JsonView(Views.Internal.class)
    public List<LimitAge> getLimitage() {
        return filmService.findAllLimitAge();
    }

    @PutMapping("/limitAge")
    public Long updateLimitAge(@RequestBody LimitAge limitAge) {
        return filmService.updateLimitAge(limitAge);
    }

    @JsonView(Views.Public.class)
    @PostMapping("/image")
    public Long loadImage(@RequestBody ImageSaveForm imageMask){
        return filmService.loadImage(imageMask);
    }

    @DeleteMapping("/image/{id}")
    public void deleteImageById(@PathVariable Long id) {
        filmService.deleteImage(id);
    }

}
