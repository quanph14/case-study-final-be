package com.codegym.webthuenha.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus status;
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    @NotNull
    private Date createTime;

    private long income;

    public long calculateIncome(Date startTime, Date endTime, long rent){
        long time;
        time = ((endTime.getTime()-startTime.getTime())/86400000) + 1;
        return time*rent;
    }

}
