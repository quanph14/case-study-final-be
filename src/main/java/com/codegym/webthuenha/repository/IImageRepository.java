package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    @Query(nativeQuery = true, value = "select  * from `images` where image_name = :name limit 1")
    Optional<Image> searchImageByImageName(@Param("name") String name);

    @Query(nativeQuery = true, value = "select images.* from images join houses_image on images.id = houses_image.image_id where house_id = :idabcd ")
    Iterable<Image> searchImageByHouseId(@Param("idabcd") Long id);
}
