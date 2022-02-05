package com.sda.project.mapper;

import com.sda.project.dto.OrderDto;
import com.sda.project.model.Order;
import com.sda.project.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {
    public OrderDto map (Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotal(order.getTotal());
        orderDto.setUserAddressCountry("Romania");
        orderDto.setUserFirstName(order.getUserFirstName());
        orderDto.setUserLastName(order.getUserLastName());
        orderDto.setOrderDtodate(order.getOrderDate());
        orderDto.setUserAddressCountry(order.getUserAddressCountry());
        orderDto.setUserAddressCounty(order.getUserAddressCounty());
        orderDto.setUserAddressLocality(order.getUserAddressLocality());
        orderDto.setUserAddressPostalCode(order.getUserAddressPostalCode());
        orderDto.setStatus("NEW");
        return orderDto;
    }

}
