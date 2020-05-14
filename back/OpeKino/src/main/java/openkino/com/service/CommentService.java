package openkino.com.service;

import openkino.com.VO.CommentVO;
import openkino.com.form.CommentForm;
import openkino.com.models.KinoUser;

import java.util.List;

public interface CommentService {
    Long addComment(KinoUser kinoUser, CommentForm comment);

    void deleteComment(Long id_comment);

    Long updateComment(CommentForm comment);

    List<CommentVO> getComments(Long filmId);
}
