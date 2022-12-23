package com.codegym.webthuenha.service.order;

import com.codegym.webthuenha.model.DTO.Income;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.service.IGeneralService;

import java.util.Date;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> getAllOrder();

    Iterable<Order> checkTimeOrder(Long id, Date startTime, Date endTime);


    Iterable<Order> getOrderPast(Long id, Long start);

    Iterable<Order> showOrderByHouseId(Long id);

    Iterable<Order> getOrderByUserId(Long id);

    Iterable<Order> getListBookingByHouseOfUserId(Long id, Long start);
    Iterable<Order> getListBookingByUserId(Long id);

    Iterable<Order> getOrderWaitConfirm(Long id, Long start);

    Iterable<Order> getOrderPast(Long id);

    Iterable<Order> getOrderWait(Long id);

    void updateStatusOrderCancel(Long id);

    Iterable<Order> showOrderByHouseIdStatus1(Long id);

    Iterable<Order> getIncome(Income income);
}
