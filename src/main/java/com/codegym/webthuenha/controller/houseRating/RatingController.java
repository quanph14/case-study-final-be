package com.codegym.webthuenha.controller.houseRating;

import com.codegym.webthuenha.model.Comment;
import com.codegym.webthuenha.model.DTO.CommentDTO;
import com.codegym.webthuenha.model.DTO.RatingDTO;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.model.Rating;
import com.codegym.webthuenha.service.house.HouseService;
import com.codegym.webthuenha.service.order.IOrderService;
import com.codegym.webthuenha.service.order.OrderService;
import com.codegym.webthuenha.service.rating.IRatingService;
import com.codegym.webthuenha.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    IRatingService ratingService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    IOrderService orderService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Rating>> HouseRating() {
        Iterable<Rating> users = ratingService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/getstar/{id}")
    public ResponseEntity<Iterable<Rating>> RatingByHouseId(@PathVariable Long id){
        return new ResponseEntity<>(ratingService.RatingByHouseId(id),HttpStatus.OK);
    }
    @PostMapping("/houserating")
    public ResponseEntity<Rating> HouseRatingDTO(@RequestBody RatingDTO ratingDTO){
        Rating rating = new Rating();
        rating.setRating(ratingDTO.getHouseRating());
        rating.setHouse(houseService.findById(ratingDTO.getHouseId()).get());
        rating.setUser(userService.findById(ratingDTO.getUserId()).get());
        ratingService.save(rating);
        return new ResponseEntity<>(rating,HttpStatus.OK);

    }

    @GetMapping("/createrating/{id}/{houses_id}")
        public ResponseEntity<Iterable<Order>> createRating(@PathVariable(name = "id") Long id, @PathVariable(name = "houses_id") Long houses_id){
            return new ResponseEntity<>(ratingService.createRating(id, houses_id),HttpStatus.OK);
    }
}

