package com.codegym.webthuenha.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HouseDTO {
    private Long id;
    private String houseName;
    private String houseAddress;
    private int bedrooms;
    private int bathrooms;
    private String description;
    private long rent;
    private List<String> listImage;
//    private String image2;
//    private String image3;
}
