package openkino.com.service;

import openkino.com.jpa.CommentDao;
import openkino.com.jpa.FilmDao;
import openkino.com.models.Comment;
import openkino.com.models.KinoUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private FilmDao filmDao;
    private CommentDao commentDao;

    public CommentServiceImpl(FilmDao filmDao, CommentDao commentDao) {
        this.filmDao = filmDao;
        this.commentDao = commentDao;
    }

    @Override
    public Long addComment(KinoUser kinoUser, Comment comment, Long id_film) {
        return commentDao.save(comment).getId();
    }


    @Override
    public void deleteComment(Long id_comment) {
        commentDao.deleteById(id_comment);
    }

    @Override
    public Long updateComment(Comment comment) {
        Comment comment1 = commentDao.findById(comment.getId()).get();
        comment1.setDate(LocalDateTime.now(ZoneId.of("UTC+4")));
        comment1.setComment(comment.getComment());
        return commentDao.save(comment1).getId();
    }

    @Override
    public List<Comment> getComments(Long id_film) {
        List<Comment> commentList = commentDao.findAllByFilmId(id_film);
        return commentList;
    }
}
