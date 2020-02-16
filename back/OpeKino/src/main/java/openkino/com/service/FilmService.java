package openkino.com.service;

import openkino.com.form.ImageForm;
import openkino.com.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilmService {

    Long saveFilm(Film film);//

    void deleteFilmById(Long id);//

    Long updateFilmById(Film film);

    Film findFilmById(Long id);//

    List<Film> findFilmsByVars(String name,
                               String genre,
                               Double rating,
                               Integer age);


    List<Film> getAllFilms();

    Long updateGenre(Genre genre);

    List<Genre> findAllGenre();

    Long updateLimitAge(LimitAge limitAge);

    List<LimitAge> findAllLimitAge();

    Long updateRating(Long idFilm, Float rating);

    Long loadImage(ImageForm imageMask) throws IOException;

    Long loadImageUpdate(MultipartFile file, Long id) throws IOException;

    Image getImage(Long id);

    void deleteImage(Long id);

    List<Image> getAllImage();
}
