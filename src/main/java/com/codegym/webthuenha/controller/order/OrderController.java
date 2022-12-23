package com.codegym.webthuenha.controller.order;

import com.codegym.webthuenha.model.DTO.Income;
import com.codegym.webthuenha.model.DTO.OrderDTO;
import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.model.OrderStatus;
import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.service.house.IHouseService;
import com.codegym.webthuenha.service.order.IOrderService;
import com.codegym.webthuenha.service.orderStatus.IOrderStatusService;
import com.codegym.webthuenha.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderStatusService orderStatusService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IHouseService houseService;


//    huy order
    @PutMapping("/cancelOrderByUser/{id}")
    public ResponseEntity<Order> cancelOrderByUser(@PathVariable Long id, @RequestBody String string){

        Optional<Order> order = orderService.findById(id);
        if (!order.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order newOrder = order.get();
        newOrder.setStatus(orderStatusService.findById(Long.parseLong("4")).get());
        orderService.save(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

//    order cho confirm
    @GetMapping("/ordersWaitConfirm/{id}/{start}")
    public ResponseEntity<Iterable<Order>> getOrderWaitConfirm(@PathVariable Long id,  @PathVariable Long start){
        return new ResponseEntity<>(orderService.getOrderWaitConfirm(id, start), HttpStatus.OK);
    }

//    order truoc day
    @GetMapping("/ordersPast/{id}/{start}")
    public ResponseEntity<Iterable<Order>> getOrderPast5(@PathVariable Long id, @PathVariable Long start){
        return new ResponseEntity<>(orderService.getOrderPast(id, start), HttpStatus.OK);
    }
    @GetMapping("/ordersPastByUser/{id}")
    public ResponseEntity<Iterable<Order>> getOrderPast(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrderPast(id), HttpStatus.OK);
    }
    @GetMapping("/ordersWaitByUser/{id}")
    public ResponseEntity<Iterable<Order>> getOrderWait(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrderWait(id), HttpStatus.OK);
    }

    // show tất cả order
    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    //    Tìm kiếm order theo id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> showOderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/house/orders/{user_id}/{start}")
    public ResponseEntity<Iterable<Order>> getListBookingByHouseOfUserId(@PathVariable Long user_id, @PathVariable Long start) {
        return new ResponseEntity<>(orderService.getListBookingByHouseOfUserId(user_id,start), HttpStatus.OK);
    }
    @GetMapping("/user/house/orders/{user_id}")
    public ResponseEntity<Iterable<Order>> getListBookingByUserId(@PathVariable Long user_id) {
        return new ResponseEntity<>(orderService.getListBookingByUserId(user_id), HttpStatus.OK);
    }

    @GetMapping("/orders/house/{house_id}")
    public ResponseEntity<Iterable<Order>> showOrderByHouseId(@PathVariable Long house_id) {
        return new ResponseEntity<>(orderService.showOrderByHouseId(house_id), HttpStatus.OK);
    }

    @PostMapping("/orders/{id}")
    public ResponseEntity<Order> createOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        List lists;
        Order order = new Order();
        lists = (List) orderService.checkTimeOrder(id, orderDTO.getStartTime(), orderDTO.getEndTime());
//        System.out.println(lists.size());
//        System.out.println(orderService.checkTimeOrder(id, orderDTO.getStartTime(), orderDTO.getEndTime()));
//        if (orderDTO.getStartTime().after(date) || orderDTO.getEndTime().after(date)) {
            if (lists.size() != 0 ) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                House house;
                house = houseService.findById(orderDTO.getHouseId()).get();
                User user;
                user = userService.findById(orderDTO.getUsersId()).get();
                OrderStatus orderStatus;
                orderStatus = orderStatusService.findById(orderDTO.getOrderStatusID()).get();

                order.setId(orderDTO.getId());
                order.setUser(user);
                order.setHouse(house);
                order.setStatus(orderStatus);
//                order.setStatus();
                order.setStartTime(orderDTO.getStartTime());
                order.setEndTime(orderDTO.getEndTime());
                order.setCreateTime(orderDTO.getCreateTime());
                order.setIncome(order.calculateIncome(orderDTO.getStartTime(), orderDTO.getEndTime(), house.getRent()));
                try {
                    orderService.save(order);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//        }
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }

    //    Xóa orders theo id
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.delete(id);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.NO_CONTENT);
    }

    //    check sửa trạng thái
    @PutMapping("/orders/changeStatus/{id}/{idStatus}")
    public ResponseEntity<Order> changeStatus(@PathVariable("id") Long id, @PathVariable("idStatus") Long idStatus) {
        Order order = orderService.findById(id).get();
//        System.out.println(id);
//        System.out.println(idStatus);
        OrderStatus orderStatus = orderStatusService.findById(idStatus).get();
        order.setStatus(orderStatus);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/showOrderByHouseIdStatus1/{id}")
    public ResponseEntity<Iterable<Order>> showOrderByHouseIdStatus1(@PathVariable Long id){
        return new ResponseEntity<>(orderService.showOrderByHouseIdStatus1(id), HttpStatus.OK);
    }

    @PostMapping("/getIncome")
    public ResponseEntity<Iterable<Order>> getIncome(@RequestBody Income income){
        return new ResponseEntity<>(orderService.getIncome(income),HttpStatus.OK);
    }
}
