package openkino.com.service;


import lombok.AllArgsConstructor;
import openkino.com.jpa.FilmDao;
import openkino.com.jpa.GenreDao;
import openkino.com.jpa.ImageDao;
import openkino.com.jpa.LimitAgeDao;
import openkino.com.form.ImageForm;
import openkino.com.models.Film;
import openkino.com.models.Genre;
import openkino.com.models.Image;
import openkino.com.models.LimitAge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {
    private FilmDao filmDao;
    private GenreDao genreDao;
    private LimitAgeDao limitAgeDao;
    private ImageDao imageDao;

    @Override
    public Long saveFilm(Film film) {
        updateGRLT(film);
        return filmDao.save(film).getId();
    }

    @Override
    public void deleteFilmById(Long id) {
        Film film = filmDao.findById(id)
                .orElseThrow(NullPointerException::new);
        if (film.getImage() != null)
            deleteImage(film.getImage().getId());
        filmDao.deleteById(id);
    }

    @Override
    public Long updateFilmById(Film film) {
        updateGRLT(film);
        Film film1 = filmDao.findById(film.getId()).get();
        film1.setName(film.getName());
        film1.setLength(film.getLength());
        return filmDao.save(film1).getId();
    }

    @Override
    public Film findFilmById(Long id) {
        return filmDao.findById(id).get();
    }

    @Override
    public Long updateGenre(Genre genre) {
        Genre genre1 = genreDao.findById(genre.getId()).get();
        genre1.setName(genre.getName());
        genre1.setFilms(genre.getFilms());
        return genreDao.save(genre1).getId();
    }

    @Override
    public List<Genre> findAllGenre() {
        return genreDao.findAll();
    }


    @Override
    public Long updateLimitAge(LimitAge limitAge) {
        LimitAge limitAge1 = limitAgeDao.findById(limitAge.getId()).get();
        limitAge1.setAge(limitAge.getAge());
        limitAge1.setFilms(limitAge.getFilms());
        return limitAgeDao.save(limitAge1).getId();
    }

    @Override
    public List<LimitAge> findAllLimitAge() {
        return limitAgeDao.findAll();
    }

    private void updateGRLT(Film film) {
        if (film.getLimitAge() != null) {
            LimitAge limitAge = film.getLimitAge();
            if (film.getLimitAge().getId() == null) {
                limitAgeDao.save(limitAge);
                film.setLimitAge(limitAge);
            }
            filmDao.save(film);
        }
        if (film.getGenre() != null) {
            Genre genre = film.getGenre();
            if (film.getGenre().getId() == null) {
                genreDao.save(genre);
                film.setGenre(genre);
            }
            filmDao.save(film);
        } else {
            filmDao.save(film);
        }
    }

    @Override
    public Long updateRating(Long idFilm, Float rating) {
        Film film = filmDao.findById(idFilm)
                .orElseThrow(NullPointerException::new);
        film.setRating(rating);
        return filmDao.save(film).getId();
    }

    @Override
    public List<Film> findFilmsByVars(String name, String genre, Double rating, Integer age) {
        if (name == null && genre == null && rating == null && age == null) {
            return null;
        }
        return filmDao.findFilmsByVars(name, genre, rating, age);
    }

    @Override
    public Long loadImage(ImageForm imageMask) throws IOException {
        Film film = filmDao.findById(imageMask.getId()).get();
        Image image = imageMask.toImage(imageMask);
        image.setFilm(film);
        film.setImage(image);
        filmDao.save(film);
        return imageDao.save(image).getId();
    }

    @Override
    public Image getImage(Long id) {
        return imageDao.findById(id).get();
    }


    @Override
    public Long loadImageUpdate(MultipartFile file, Long id) throws IOException {
        Image image = imageDao.findById(id).get();
        byte[] array = file.getBytes();
        image.setImage_array(array);
        image.setType(file.getContentType());
        return imageDao.save(image).getId();
    }

    @Override
    public List<Film> getAllFilms() {
        return filmDao.findAll();
    }

    @Override
    public void deleteImage(Long id) {
        Image image = imageDao.findById(id).get();
        Long id_kino = image.getFilm().getId();
        Film film = filmDao.findById(id_kino).get();
        film.setImage(null);
        filmDao.save(film);
        imageDao.deleteById(id);
    }

    @Override
    public List<Image> getAllImage() {
        return imageDao.findAll();
    }
}
