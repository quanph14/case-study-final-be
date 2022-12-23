package com.codegym.webthuenha.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long usersId;
    private Long houseId;
    private Long orderStatusID;
    private Date startTime;
    private Date endTime;
    private Date createTime;

}
