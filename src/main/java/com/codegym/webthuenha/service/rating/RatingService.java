package com.codegym.webthuenha.service.rating;

import com.codegym.webthuenha.model.Comment;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.model.Rating;
import com.codegym.webthuenha.repository.IOrderRepository;
import com.codegym.webthuenha.repository.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    @Autowired
    IRatingRepository ratingRepository;
    @Autowired
    IOrderRepository orderRepository;
    @Override
    public Iterable<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }

    public Iterable<Rating> RatingByHouseId(Long id){
        return ratingRepository.HouseRating(id);
    }

    @Override
    public Iterable<Order> createRating(Long id, Long houses_id) {
        return orderRepository.createRating(id, houses_id);
    }
}
