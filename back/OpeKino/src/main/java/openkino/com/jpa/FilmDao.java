package openkino.com.jpa;

import openkino.com.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmDao extends JpaRepository<Film, Long> {

    @Query("select f from Film f join f.genre g where g.id = :id")
    List<Film> findAllByGenreId(@Param("id") Long id);

    @Query(value = " SELECT FILM.*" +
            " FROM FILM " +
            "    inner join GENRE on GENRE.ID = FILM.ID_GENRE " +
            "    inner join LIMIT_AGE on FILM.ID_LIMIT_AGE = LIMIT_AGE.ID " +
            " WHERE (upper(FILM.NAME) LIKE CONCAT('%',upper(:name1),'%') OR FILM.NAME IS NULL) AND " +
            "    (upper(GENRE.NAME) LIKE CONCAT('%',upper(:genre),'%') OR GENRE.NAME IS NULL) AND " +
            "    (FILM.rating = :rating OR :rating IS NULL) AND " +
            "    (LIMIT_AGE.AGE = :age OR :age IS NULL)", nativeQuery = true)
    List<Film> findFilmsByVars(@Param("name1") String name,
                               @Param("genre") String genre,
                               @Param("rating") Double rating,
                               @Param("age") Integer age);
}
