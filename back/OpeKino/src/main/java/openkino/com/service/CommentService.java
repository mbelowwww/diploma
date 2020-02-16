package openkino.com.service;

import openkino.com.models.Comment;
import openkino.com.models.KinoUser;

import java.util.List;

public interface CommentService {
    Long addComment(KinoUser kinoUser, Comment comment, Long id_film);

    void deleteComment(Long id_comment);

    Long updateComment(Comment comment);

    List<Comment> getComments(Long id_film);
}
