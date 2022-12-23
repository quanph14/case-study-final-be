package com.codegym.webthuenha.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingDTO {
    private Long id;
    private Long houseId;    private Long userId;
    private String houseRating;
}
