package com.codegym.webthuenha.service.image;

import com.codegym.webthuenha.model.Image;
import com.codegym.webthuenha.service.IGeneralService;

import java.util.Optional;

public interface IImageService extends IGeneralService<Image> {
    Optional<Image> findByName(String name);

    Iterable<Image> findByHouseId(Long id);
}
