package openkino.com.service;


import lombok.AllArgsConstructor;
import openkino.com.form.ImageSaveForm;
import openkino.com.jpa.FilmDao;
import openkino.com.jpa.GenreDao;
import openkino.com.jpa.ImageDao;
import openkino.com.jpa.LimitAgeDao;
import openkino.com.models.Film;
import openkino.com.models.Genre;
import openkino.com.models.Image;
import openkino.com.models.LimitAge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        Helper.auditOnCreate(film);
        return filmDao.save(film).getId();
    }

    @Override
    public void deleteFilmById(Long id) {
        List<Image> images = imageDao.findAllByFilm_Id(id);
        imageDao.deleteAll(images);
        filmDao.delete(filmDao.findById(id).orElseThrow(NullPointerException::new));
    }

    @Override
    public Long updateFilmById(Film film) {
        updateGRLT(film);
        Film film1 = filmDao.findById(film.getId()).get();
        film1.setName(film.getName());
        film1.setLength(film.getLength());
        Helper.auditOnUpdate(film);
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
        Helper.auditOnUpdate(genre1);
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
        Helper.auditOnCreate(limitAge1);
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
    public Long loadImage(ImageSaveForm imageMask){
        Film film = filmDao.findById(imageMask.getFilmId()).orElseThrow(NullPointerException::new);
        List<Image> images = imageMask.getImages().stream().map(imageForm ->{
            Image image = imageForm.toImage();
            image.setFilm(film);
            Helper.auditOnUpdate(image);
            return image;
        }).collect(Collectors.toList());
        imageDao.saveAll(images);
        return film.getId();
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
        Helper.auditOnUpdate(image);
        return imageDao.save(image).getId();
    }

    @Override
    public List<Film> getAllFilms() {
        return filmDao.findAll();
    }

    @Override
    public void deleteImage(Long id) {
    }

    @Override
    public List<Image> getAllImage() {
        return imageDao.findAll();
    }
}
