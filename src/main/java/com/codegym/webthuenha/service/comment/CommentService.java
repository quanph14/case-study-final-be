package com.codegym.webthuenha.service.comment;

import com.codegym.webthuenha.model.Comment;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    @Autowired
    ICommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
    commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> CommentByHouseId(Long id) {
        return commentRepository.HouseComment(id);
    }

    @Override
    public Iterable<Order> createComment(Long id, Long houses_id) {
        return null;
    }

    @Override
    public Iterable<Comment> getListCommentByHouseOfUserId(Long userId, Long start) {
        return commentRepository.getListCommentByHouseOfUserId(userId, 5 * start);
    }
        @Override
    public Iterable<Comment> getAllByCommentAndIsReadTrue(Long userId) {
        return commentRepository.getAllByCommentAndIsReadTrue(userId);
    }

    @Override
    public Iterable<Comment> getAllByCommentAndIsReadFalse(Long userId) {
        return commentRepository.getAllByCommentAndIsReadFalse(userId);
    }

    @Override
    public Iterable<Comment> getCommentByHouseId(Long id, long start) {
        return commentRepository.getCommentByHouseId(id, start * 5);
    }
}
