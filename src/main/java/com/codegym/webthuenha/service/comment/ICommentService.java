package com.codegym.webthuenha.service.comment;

import com.codegym.webthuenha.model.Comment;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.model.Rating;
import com.codegym.webthuenha.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    public Iterable<Comment> CommentByHouseId(Long id);
    public Iterable<Order> createComment(Long id, Long houses_id);
    Iterable<Comment> getListCommentByHouseOfUserId(Long userId, Long start);
    Iterable<Comment> getAllByCommentAndIsReadTrue(Long userId);
    Iterable<Comment> getAllByCommentAndIsReadFalse(Long userId);

    Iterable<Comment> getCommentByHouseId(Long id, long start);
}
