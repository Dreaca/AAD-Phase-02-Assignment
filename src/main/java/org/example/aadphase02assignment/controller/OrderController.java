package org.example.aadphase02assignment.controller;

import org.example.aadphase02assignment.dto.impl.OrderDTO;
import org.example.aadphase02assignment.exceptions.DataPersistException;
import org.example.aadphase02assignment.exceptions.OrderNotFoundException;
import org.example.aadphase02assignment.service.OrderService;
import org.example.aadphase02assignment.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v3/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO order) {
        try {
            orderService.saveOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(name = "/orderId",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrderId(){
        return AppUtil.generateOrderId();
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping(value = "${orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") String orderId){
        try {
            orderService.getOrder(orderId);
            return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
        }catch(OrderNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "${orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") String orderId){
        try{
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(OrderNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "${orderId}")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDTO order, @PathVariable("orderId") String orderId){
        try {
            orderService.updateOrder(order,orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(OrderNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
