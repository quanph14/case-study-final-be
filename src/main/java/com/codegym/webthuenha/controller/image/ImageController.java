package com.codegym.webthuenha.controller.image;


import com.codegym.webthuenha.model.Image;
import com.codegym.webthuenha.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageController {

    @Autowired
    IImageService imageService;

    @GetMapping("/lists")
    public ResponseEntity<Iterable<Image>> showAllUser() {
        Iterable<Image> images = imageService.findAll();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> findImageById(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image -> new ResponseEntity<>(image, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imageService.delete(id);
        return new ResponseEntity<>(imageOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("house/{id}")
    public ResponseEntity<Iterable<Image>> findByHouseId(@PathVariable Long id){
        Iterable<Image> imageIterable = imageService.findByHouseId(id);
        return new ResponseEntity<>(imageIterable, HttpStatus.OK);
    }
}
