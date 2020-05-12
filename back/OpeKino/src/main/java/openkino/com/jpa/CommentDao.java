package openkino.com.jpa;

import openkino.com.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findAllByFilmId(Long id_film);

    List<Comment> findAllByKinoUser_Id(Long kinoUserId);
}
