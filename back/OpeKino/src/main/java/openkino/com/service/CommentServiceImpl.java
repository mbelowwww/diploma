package openkino.com.service;

import lombok.AllArgsConstructor;
import openkino.com.VO.CommentVO;
import openkino.com.exceptions.ResponseException;
import openkino.com.form.CommentForm;
import openkino.com.jpa.CommentDao;
import openkino.com.jpa.FilmDao;
import openkino.com.jpa.KinoUserDao;
import openkino.com.models.Comment;
import openkino.com.models.KinoUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final FilmDao filmDao;
    private final KinoUserDao kinoUserDao;


    @Override
    @Transactional
    public Long addComment(KinoUser kinoUser, CommentForm comment) {
        KinoUser user = null;
        if (kinoUser != null)
            if (kinoUser.getMail() != null)
                user = kinoUserDao.findByMale(kinoUser.getMail());
        return commentDao.save(comment.toComment(user,filmDao)).getId();
    }

    @Override
    @Transactional
    public void deleteComment(Long id_comment) {
        Comment comment = commentDao.findById(id_comment)
                .orElseThrow(()-> new ResponseException(HttpStatus.BAD_REQUEST,"Коментраий не найден ID: " + id_comment));
        commentDao.delete(comment);
    }

    @Override
    @Transactional
    public Long updateComment(CommentForm commentForm) {
        Comment comment = commentDao.findById(commentForm.getId())
                .orElseThrow(()-> new ResponseException(HttpStatus.BAD_REQUEST,"Коментраий не найден ID: " + commentForm.getId()));
        return commentForm.update(comment,filmDao).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentVO> getComments(Long filmId) {
        return commentDao.findAllByFilmId(filmId).stream()
                .map(CommentVO::new)
                .collect(Collectors.toList());
    }

}
