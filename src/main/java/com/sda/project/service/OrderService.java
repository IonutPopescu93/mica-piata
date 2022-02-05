package com.sda.project.service;

import com.sda.project.dto.OrderDto;
import com.sda.project.mapper.OrderMapper;
import com.sda.project.model.Order;
import com.sda.project.model.OrderStatus;
import com.sda.project.model.ShoppingCart;
import com.sda.project.model.User;
import com.sda.project.repository.OrderRepository;
import com.sda.project.repository.ShoppingCartRepository;
import com.sda.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;

    public OrderService(UserRepository userRepository, OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository, UserService userService, OrderMapper orderMapper, ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
    }


    public void save(OrderDto orderDto, String email, String sessionToken) {
        Order order = new Order();
        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
        Optional<User> userOptional = userRepository.findByEmail(email);
        order.setUserAddressCountry("Romania");
        order.setUserFirstName(orderDto.getUserFirstName());
        order.setUserLastName(orderDto.getUserLastName());
        order.setOrderDate(LocalDateTime.now());
        order.setUserAddressCountry(orderDto.getUserAddressCountry());
        order.setUserAddressCounty(orderDto.getUserAddressCounty());
        order.setUserAddressLocality(orderDto.getUserAddressLocality());
        order.setUserAddressPostalCode(orderDto.getUserAddressPostalCode());
        order.setUser(userOptional.get());
        order.setShoppingCart(shoppingCart);
        order.setTotal(shoppingCart.getTotalPrice());
        order.setStatus(OrderStatus.valueOf("NEW"));
        orderRepository.save(order);
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> orderMapper.map(order))
                .collect(Collectors.toList());
    }
}
